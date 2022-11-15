#include <SoftwareSerial.h>
#include <Wire.h>
//#include <Adafruit_Sensor.h>
#include <Adafruit_BMP085_U.h>
#include "DHT.h"

String tmp ="";
long previousTime=0;
long interval=2000;

DHT dht(2, DHT11);
SoftwareSerial zigbee(12,13);
Adafruit_BMP085_Unified bmp = Adafruit_BMP085_Unified(10085);
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  zigbee.begin(9600);
  zigbee.setTimeout(200);
  zigbee.listen();

  pinMode(A0, INPUT);
  pinMode(A1, INPUT);
  pinMode(A2, INPUT);
  pinMode(A3, INPUT);
  dht.begin();
  bmp.begin();
}

void loop() {
  
  int aR0 =analogRead(A0);
  int aR1 =analogRead(A1);
  int aR2 =analogRead(A2);
  
  int h = dht.readHumidity();
  int t = dht.readTemperature();

  //sensors_event_t event;
 // bmp.getEvent(&event);
  unsigned long currentTime=millis();
  if( currentTime - previousTime >interval){

    tmp="-01";

    tmp=tmp+"*01"+(String)aR0;
    tmp=tmp+"*02"+(String)aR1;
    tmp=tmp+"*03"+(String)aR2;
    tmp=tmp+"*04"+(String)h;
    tmp=tmp+"*05"+(String)t;
   // tmp=tmp+"*06"+(String)event.pressure;
    tmp=tmp+"/";
    zigbee.println(tmp);
    Serial.println(tmp);
    tmp="";
    previousTime = currentTime;
    
  }
   while(zigbee.available() >0){
   tmp +=char(zigbee.read());
   delay(10);
  }
  if(tmp.length()>0){
    String tmp0 =tmp.substring(0,3);
 
    if(tmp0 == "+00" ||tmp0 == "+01" ){
      tmp0 =tmp.substring(3,5);
      if(tmp0 == "AT") {
        zigbee.println("=01OK");
       }
    }
    tmp="";
  }
}
