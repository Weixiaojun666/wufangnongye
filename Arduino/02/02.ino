#include <SoftwareSerial.h>
  
SoftwareSerial zigbee(12,13);
int g1 =0;
int g2 =0;
int g3 =0;
int g4 =0;
int g5 =0;
int g6 =0;
int g7 =0;
int g8 =0;
int g1t =0;
int g2t =0;
int g3t =0;
int g4t =0;
int g5t =0;
int g6t =0;
int g7t =0;
int g8t =0;
String tmp ="";
void setup() {
  Serial.begin(9600);
  zigbee.begin(9600);
  zigbee.setTimeout(200);
  zigbee.listen();
  // put your setup code here, to run once:
  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
  
}

void loop() {
  // put your main code here, to run repeatedly:
  
  if (g1 == 1 && g1!=g1t) {
    digitalWrite(2, HIGH);
    g1t =1;
  }
  if (g2 == 1 && g2!=g2t) {
    digitalWrite(3, HIGH);
    g2t =1;
  }
  if (g3 == 1 && g3!=g3t){
    digitalWrite(4, HIGH);
    g3t =1;
  }
  if (g4 == 1 && g4!=g4t) {
    digitalWrite(5, HIGH);
    g4t =1;
  }
  if (g5 == 1 && g5!=g5t) {
    digitalWrite(6, HIGH);
    g5t =1;
  }
  if (g6 == 1 && g6!=g6t) {
    digitalWrite(7, HIGH);
    g6t =1;
  }
  if (g7 == 1 && g7!=g7t) {
    digitalWrite(8, HIGH);
    g7t =1;
  }
  if (g8 == 1 && g8!=g8t) {
    digitalWrite(9, HIGH);
    g8t =1;
  }
  
  if (g1 == 0 && g1!=g1t) {
    digitalWrite(2,LOW);
    g1t =0;
  }
  if (g2 == 0 && g2!=g2t) {
    digitalWrite(3,LOW);
    g2t =0;
  }
  if (g3 == 0 && g3!=g3t) {
    digitalWrite(4,LOW);
    g3t =0;
  }
  if (g4 == 0 && g4!=g4t) {
    digitalWrite(5,LOW);
    g4t =0;
  }
  if (g5 == 0 && g5!=g5t) {
    digitalWrite(6,LOW);
    g5t =0;
  }
  if (g6 == 0 && g6!=g6t) {
    digitalWrite(7,LOW);
    g6t =0;
  }
  if (g7 == 0 && g7!=g7t) {
    digitalWrite(8,LOW);
    g7t =0;
  }
  if (g8 == 0 && g8!=g8t) {
    digitalWrite(9,LOW);
    g8t =0;
  }

  while(zigbee.available() >0){
   tmp +=char(zigbee.read());
   delay(10);
  }
  if(tmp.length()>0){
    String tmp0 =tmp.substring(0,3);
 
    if(tmp0 == "+00" ||tmp0 == "+02" ){
      tmp0 =tmp.substring(3,5);
      if(tmp0 == "AT") {
        zigbee.println("=02OK");
       }
       if(tmp0 == "ST") {
        tmp0 =tmp.substring(5,6);
        if (tmp0 == "0"){
          g1 =1;
          g2 =1;
          g3 =1;
          g4 =1;
          g5 =1;
          g6 =1;
          g7 =1;
          g8 =1;
        }
        if (tmp0 == "1"){
          g1 =1;
        }
        if (tmp0 == "2"){
          g2 =1;
        }
        if (tmp0 == "3"){
          g3 =1;
        }
        if (tmp0 == "4"){
          g4 =1;
        }
        if (tmp0 == "5"){
          g5 =1;
        }
        if (tmp0 == "6"){
          g6 =1;
        }
        if (tmp0 == "7"){
          g7 =1;
        }
        if (tmp0 == "8"){
          g8 =1;
        }
        zigbee.println("=02OK");
       }
        if(tmp0 == "CL") {
        tmp0 =tmp.substring(5,6);
         if (tmp0 == "0"){
          g1 =0;
          g2 =0;
          g3 =0;
          g4 =0;
          g5 =0;
          g6 =0;
          g7 =0;
          g8 =0;
        }
        if (tmp0 == "1"){
          g1 =0;
        }
        if (tmp0 == "2"){
          g2 =0;
        }
        if (tmp0 == "3"){
          g3 =0;
        }
        if (tmp0 == "4"){
          g4 =0;
        }
        if (tmp0 == "5"){
          g5 =0;
        }
        if (tmp0 == "6"){
          g6 =0;
        }
        if (tmp0 == "7"){
          g7 =0;
        }
        if (tmp0 == "8"){
          g8 =0;
        }
        zigbee.println("=02OK");
       }   
    }
    tmp="";
    }
}
