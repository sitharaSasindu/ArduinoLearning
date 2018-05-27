#include <SoftwareSerial.h> // use the software uart
SoftwareSerial bluetooth(12, 13); // RX, TX
char input; //getting the inputs through a char variable

#define echoPinOne 7 // Echo Pin
#define trigPinOne 8 // Trigger Pin
#define echoPinTwo 4 // Echo Pin
#define trigPinTwo 3 // Trigger Pin

int maximumRange = 50; // Maximum range needed
int minimumRange = 0; // Minimum range needed
static long duration, distance; // Duration used to calculate distance
boolean sonar1;
boolean sonar2;

void setup() {

 pinMode(trigPinOne, OUTPUT);
 pinMode(echoPinOne, INPUT);
 pinMode(trigPinTwo, OUTPUT);
 pinMode(echoPinTwo, INPUT);
 pinMode(11,OUTPUT);   //left motors forward
 pinMode(10,OUTPUT);   //left motors reverse
 pinMode(6,OUTPUT);   //right motors forward
 pinMode(5,OUTPUT);   //right motors reverse

  //begining the serial connection
 Serial.begin(9600);
 bluetooth.begin(115200);
 delay(500); 
}

void loop() {

 //triggerUltraSonicOne();
 //triggerUltraSonicTwo();

  if(bluetooth.available()) { //checking whether the bluetooth connection is available
  input = bluetooth.read(); // initializing the input variable with reciving bluetooth signal
  bluetooth.println(input); //getting that signal as input

if(input == '1'){            //move forward(all motors rotate in forward direction)
  driveForward();
  Serial.println("forward");
      }
      
  else if(input == '2'){      //move reverse (all motors rotate in reverse direction)
  driveBackward();
  Serial.println("backward");
      }

else if(input == '3'){      //turn right (left side motors rotate in forward direction, right side motors doesn't rotate)
  driveRight();
  Serial.println("right");
      }

else if(input == '4'){      //turn left (right side motors rotate in forward direction, left side motors doesn't rotate)
  driveLeft();
  Serial.println("left");
      }

else if(input == '5'){      //STOP (all motors stop)
  stopMotors();
  Serial.println("stopnow");
  Serial.println(input);
     }

else if(input == 'k'){
  do{
    objectDetection(); //calling the object detection method so that vehicle can move avoiding the objects
    Serial.println(input);
    input = bluetooth.read();
    break;
  }while(input != 'k');
   }
  }
 }


static void objectDetection(){

  //first sonar
  
Serial.println(input);
Serial.println("doneeeeeeeeeeeeeeeeeeeee");

  triggerUltraSonicOne();
 
if (distance > maximumRange || distance < minimumRange){
  stopMotors();
  Serial.println("finallyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");

  sonar1 = false;
  
  digitalWrite(11,HIGH); 
  digitalWrite(10,LOW);
  digitalWrite(6,LOW);
  digitalWrite(5,HIGH);
}
 else {

  sonar1 = true;
  
  stopMotors();
  
  delay(250);
  
  digitalWrite(11,HIGH);
  digitalWrite(10,LOW);
  digitalWrite(6,LOW);
  digitalWrite(5,LOW);
  
 delay(500);
 }

triggerUltraSonicTwo();

  //second sonar

if (distance > maximumRange || distance < minimumRange){
 /* Send a positive number to computer and Turn MOTORS ON 
 to indicate "out of range" */

 Serial.println("notttttttttttttttttttttttttttttttttttttttttt");


  sonar2 = false;
  
  digitalWrite(11,HIGH); 
  digitalWrite(10,LOW);
  digitalWrite(6,LOW);
  digitalWrite(5,HIGH);

}
 else {

  sonar2 = true;
  
  stopMotors();
  
  delay(250);
  
  digitalWrite(11,LOW);
  digitalWrite(10,LOW);
  digitalWrite(6,LOW);
  digitalWrite(5,HIGH);
  
 delay(500);
 }
 }

void triggerUltraSonicOne(){
 digitalWrite(trigPinOne, LOW); 
 delayMicroseconds(2); 

 digitalWrite(trigPinOne, HIGH);
 delayMicroseconds(10); 
 
 digitalWrite(trigPinOne, LOW);
 duration = pulseIn(echoPinOne, HIGH);
 
 //Calculate the distance (in cm) based on the speed of sound.
 distance = duration/58.2;
}

void triggerUltraSonicTwo(){
 digitalWrite(trigPinTwo, LOW); 
 delayMicroseconds(2); 

 digitalWrite(trigPinTwo, HIGH);
 delayMicroseconds(10); 
 
 digitalWrite(trigPinTwo, LOW);
 duration = pulseIn(echoPinTwo, HIGH);
 
 //Calculate the distance (in cm) based on the speed of sound.
 distance = duration/58.2;
}

  void stopMotors(){
  digitalWrite(11,LOW);
  digitalWrite(10,LOW);
  digitalWrite(6,LOW);
  digitalWrite(5,LOW);
  }

  //drive all moters forward
  void driveForward(){
  digitalWrite(11,HIGH); 
  digitalWrite(10,LOW);
  digitalWrite(6,LOW);
  digitalWrite(5,HIGH);
    }

  //drive all moters backward
  void driveBackward(){
  digitalWrite(11,LOW);
  digitalWrite(10,HIGH);
  digitalWrite(6,HIGH);
  digitalWrite(5,LOW);
    }

  //drive all moters left
  void driveLeft(){
  digitalWrite(11,LOW);
  digitalWrite(10,LOW);
  digitalWrite(6,LOW);
  digitalWrite(5,HIGH);
    }

  //drive all moters right
  void driveRight(){
    digitalWrite(11,HIGH);
  digitalWrite(10,LOW);
  digitalWrite(6,LOW);
  digitalWrite(5,LOW);
    }

