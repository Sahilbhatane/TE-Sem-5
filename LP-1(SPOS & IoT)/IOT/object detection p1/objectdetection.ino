void setup() {
    pinmode(13, OUTPUT);
    pinmode(3, INPUT);
    Serial.begin(9600);
}
void loop() {
    if (digitalread(3)== LOW) {
        digitalwrite(13, HIGH);
        delay(10)
    }
    else {
        digitalwrite(13, LOW);
        delay(1000);
    }
}