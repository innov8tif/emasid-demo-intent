# emasid-demo-intent
Quickly integrate with [EMAS ID Demo](https://play.google.com/store/apps/details?id=com.innov8tif.emas.id.demo) Android app via Intent, to achieve ID card OCR scanning and facial verification.

This project demonstrates how you can launch EMAS ID Demo app from another app (e.g. your user onboarding app) via Intent. EMAS ID Demo can be used to scan (using OCR) various types of ID document and passport, and return the result to your app. EMAS ID Demo app can be launched as follow : 

     Intent intent = new Intent();
     intent.setAction("com.innov8tif.emas.scan");
     intent.putExtra(EXTRA_SCAN_TYPE, "mykad");
     startActivityForResult(intent, RC_EMAS);

**Available Scanner Types**


|Scan Type | Value |
|--|--|
| MyKad | mykad |
| iKad | ikad |
| Passport | passport |
| Malaysia's driving license | mdr |
|  Singapores's IC | sg|
| Indonesia's eKTP  | ektp |
| Hong Kong's IC | hk |

Face verification feature can be turned on by passing extra into Intent:

    intent.putExtra(EXTRA_VERIFY_FACE, true);
    intent.putExtra(EXTRA_HIGH_THRESHOLD, true);
    
There are two types of face verification available and which can be selected by passing an intent :

`intent.putExtra(EXTRA_FACE_VERIFICATION_TYPE, "facepp");`

**Available Face Verification Type**

|Face Verification Type| Value |
|--|--|
| Face++ | facepp |
| YITU | yitu |

When ID card scanning completed, you will receive scan result as JSON string in **onActivityResult()** method. And the resulted JSON is in the following format:

    {  
    "confidenceLevel":27.641726,  
    "contentModelList":[  
	    {  
	    "label":"Name",  
	    "value":"ROWAN SEBASTIAN\nATKINSON"  
	    },  
	    {  
	    "label":"New IC Number",  
	    "value":"550106-12-5821"  
	    },  
	    {  
	    "label":"Gender",  
	    "value":"Male"  
	    },  
	    {  
	    "label":"Birthday",  
	    "value":"6-1-1955"  
	    },  
	    {  
	    "label":"Address",  
    "	value":"GDW KAMPUNG BAYANGAN, I\n80000 KENINGAU\nSABAH"  
	    }  
	],  
    "detectedFacePath":"/storage/emulated/0/EMAS ID Demo/IMG-1532596927824.jpg",  
    "documentPath":"/storage/emulated/0/EMAS ID Demo/IMG-1532596914261.jpg",  
    "threshold":79.9  
    }

***Please make sure that EMAS ID Demo app version 1.2.0 is installed in your Android device. For more detail information, please refer to the sample project source code**
