#include <jni.h>
#include <stdio.h>
#include "com_pawbla_project_home_embedded_sensor_reader_DHTJniReader.h"
#include "pi_dht_read.h"

JNIEXPORT jint JNICALL Java_com_pawbla_project_home_embedded_sensor_reader_DHTJniReader_read(JNIEnv *env, jobject thisObj, jint type, jint pin) {
   jclass thisClass = (*env)->GetObjectClass(env, thisObj);

   jfieldID hFieldNo = (*env)->GetFieldID(env, thisClass, "humidity", "F");
   jfieldID tFieldNo = (*env)->GetFieldID(env, thisClass, "temperature", "F");

   jfloat humidity;
   jfloat* pHumidity = &humidity;
   jfloat temperature;
   jfloat* pTemperature = &temperature;

   int respCode = pi_dht_read(type, pin, pHumidity, pTemperature);
   (*env)->SetFloatField(env, thisObj, hFieldNo, humidity);
   (*env)->SetFloatField(env, thisObj, tFieldNo, temperature);

   return respCode;
}