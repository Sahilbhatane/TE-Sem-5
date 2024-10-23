import Adafruit_DHT
import RPi.GPIO as GPIO
import time
GPIO.setmode(GPIO.BCM)
GPIO.setup(21,GPIO.OUT) # LED on 21 pin
while True:
    humidity, temp = Adafruit_DHT.read_retry(11, 17) # data pin of DHT11 sensor on GPIO 11
    print ("Humidity = {} %; Temperature = {} C".format(humidity, temp))
    if(temp>25):
#threshold value 25
        print ("value is greate than 25")
        GPIO.output(21,True)
        time.sleep(2)
        GPIO.output(21,False)
    else:
        print ("temperature if below 25")
        GPIO.output(21,False)
        time.sleep(2)