package com.example.luminanceCtrl;


/**
 *  from http://apps.hi.baidu.com/share/detail/53623456
 */
import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.view.WindowManager;

public class luminanceCtrl {
	/* Android����Ļ���Ⱥ�����2.1+��ʱ���ṩ���Զ����ڵĹ��ܣ�
	 * ���ԣ�����������Զ����ڹ��ܵ�ʱ�� ���ǽ��е��ں�����û��һ�����õģ�
	 * ������Һ���������ֻ�н����жϣ����Ƿ�������Ļ���ȵ��Զ����ڹ��ܡ�
	 */

	/** * �ж��Ƿ������Զ����ȵ��� */

	public static boolean isAutoBrightness(ContentResolver aContentResolver) {    
		boolean automicBrightness = false;    
		try{        
			automicBrightness = Settings.System.getInt(aContentResolver,                
			Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;   
		 } 
		catch(SettingNotFoundException e) 
		{       
			e.printStackTrace();  
		 }    
		return automicBrightness;
	}
	//Ȼ�����Ҫ���õ�ǰ�������ˣ�����ͱȽϾ����ˣ�
	/** * ��ȡ��Ļ������ */
	public static int getScreenBrightness(Activity activity) {   
		int nowBrightnessValue = 0;    
		ContentResolver resolver = activity.getContentResolver();    
		try{        
			nowBrightnessValue = android.provider.Settings.System.getInt(resolver, Settings.System.SCREEN_BRIGHTNESS);  
		}
		catch(Exception e) {       
			e.printStackTrace();  
		}    
		return nowBrightnessValue;
	}
	//������޸���Ļ�������أ�
	/** * �������� */
	public static void setBrightness(Activity activity, int brightness) {   
	 // Settings.System.putInt(activity.getContentResolver(),    
	// Settings.System.SCREEN_BRIGHTNESS_MODE,    
	// Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);    
		//ContentResolver resolver = activity.getContentResolver();
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();   
		lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);  
		Log.d("lxy", "set  lp.screenBrightness == " + lp.screenBrightness);
		activity.getWindow().setAttributes(lp); 
	 }
	//��ô���������ˣ�����Ϊʲô���ǻ���֣������ˣ�û��ӳ�أ�
	//�ٺ٣�������Ϊ���������Զ����ڹ����ˣ�����ιر��أ����������Ҫ�ģ�

	/** * ֹͣ�Զ����ȵ��� */
	public static void stopAutoBrightness(Activity activity) {   
		 Settings.System.putInt(activity.getContentResolver(),          
		 Settings.System.SCREEN_BRIGHTNESS_MODE,           
		 Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
	 }
	//�ܿ���������ȻӦ���ܹر���ӴӴ������ô�ر��أ��ܼ򵥵ģ�

	/** * ���������Զ����� *  

	* @param activity */	 
	public static void startAutoBrightness(Activity activity) {   
		Settings.System.putInt(activity.getContentResolver(),           
		Settings.System.SCREEN_BRIGHTNESS_MODE,            
		Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
	}
	
	//���ˣ�Ӧ��˵�������ȵĲ�඼���ˣ�������
	//����������Ϊ��Ӧ�ý����ˣ����Ǳ�����ǣ���Ȼ��ղ��������õĻ���ֻ���ڵ�ǰ��activity�������ã�һ���˳���ʱ�򣬻ᷢ�ֺ������ã����磬ԭ���������˱����ˡ�����
	/** * ������������״̬ */
	public static void saveBrightness(ContentResolver resolver, int brightness) {    
		Uri uri = android.provider.Settings.System.getUriFor("screen_brightness");   
		android.provider.Settings.System.putInt(resolver, "screen_brightness", brightness);    
		// resolver.registerContentObserver(uri, true, myContentObserver);   
		resolver.notifyChange(uri, null);
		}
}