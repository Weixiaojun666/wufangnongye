String tmp="";
char tmpc[100];
void connectNBIOT (String cmd, char *res)
{
  while(1)
  {
    Serial1.println(cmd);
    delay(500);
    while(Serial1.available()>0)
    {
      if(Serial1.find(res))
      {
  digitalWrite(LED_BUILTIN, HIGH);   // turn the LED on (HIGH is the voltage level)
  delay(100);              // wait for a second
  digitalWrite(LED_BUILTIN, LOW);    // turn the LED off by making the voltage LOW
  delay(100);     
        return;
      }
    }
    delay(1000);
   }
}



void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  //nbiot
  Serial1.begin(115200);
  //zigbee
  Serial3.begin(115200);


   connectNBIOT("AT",(char*)"OK");
   Serial1.println("ATE0&W");
   delay(300);  
   connectNBIOT("AT+CPIN?",(char*)"+CPIN: READY");
   connectNBIOT("AT+CGATT?",(char*)"+CGATT: 1");
   Serial1.println("AT+QICLOSE=0");
   delay(500);
   connectNBIOT("AT+QIOPEN=1,0,\"TCP\",\"39.105.158.238\",47777,0,1",(char*)"+QIOPEN: 0,0");

  Serial1.println("AT+CGSN=1");
  delay(500);
  while(Serial1.available() >0){
   tmp +=char(Serial1.read());
   delay(10);
  }
  tmp=tmp.substring(tmp.length()-23,tmp.length()-8);
   tmp="AT+QISEND=0,39,wbxiot:"+tmp+":Q20urTYuupcppp6M";
   connectNBIOT(tmp,(char*)"wbxiot:verification success!");
   //Serial1.println(tmp);
   tmp="";
   delay(300);

   Serial.println("TCP CONNECT IS OK");
}

void loop() {
  // put your main code here, to run repeatedly:

//nbiot
while(Serial1.available() >0){
   tmp +=char(Serial1.read());
   delay(10);
}
if(tmp.length()>0){
  Serial.println(tmp);
  tmp="";
}


//zigbee
while(Serial3.available() >0){
   tmp +=char(Serial3.read());
   delay(10);
}
if(tmp.length()>0){
  String tmp0 =tmp.substring(0,1);
  if(tmp0 == "-"||tmp0 == "="){
    tmp0=tmp.substring(tmp.length()-3,tmp.length()-2);
    if(tmp0=="/"){
      tmp=tmp.substring(0,tmp.length()-2);
      //tmp="AT+QISEND=0,"+tmp.length()+','+tmp;
      tmp0="AT+QISEND=0,"+(String)tmp.length()+(String)','+tmp;
      Serial.println(tmp0);
      Serial1.println(tmp0);
      
    }
  }
  tmp="";
}





//pc -> nbiot
while(Serial.available() >0){
   tmp +=char(Serial.read());
   delay(10);
}
if(tmp.length()>0){
  Serial1.println(tmp);
  tmp="";
}
}
