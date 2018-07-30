# emasid-demo-intent
Quickly integrate with [EMAS ID Demo](https://play.google.com/store/apps/details?id=com.innov8tif.emas.id.demo) Android app via Intent, to achieve ID card OCR scanning and facial verification.

This project demonstrates how you can launch EMAS ID Demo app from another app (e.g. your user onboarding app) via Intent. EMAS ID Demo can be used to scan (using OCR) various types of ID document and passport, and return the result to your app. EMAS ID Demo app can be launched as follow : 

     Intent intent = new Intent();
     intent.setAction("com.innov8tif.emas.scan");
     intent.putExtra(EXTRA_SCAN_TYPE, "mykad");
     startActivityForResult(intent, RC_EMAS);

**Available Scanner Types Are**

 1. mykad for MyKad
 2. ikad for IKad
 3. passport for Passport
 4. mdr for Malaysia's driving license
 5. sg for Singapores's IC
 6. ektp for Indonesia's eKTP and
 7. hk for Hong Kong's IC

Face verification feature can be turned on by passing extra into Intent:

    intent.putExtra(EXTRA_VERIFY_FACE, true);
    intent.putExtra(EXTRA_HIGH_THRESHOLD, true);
    
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
