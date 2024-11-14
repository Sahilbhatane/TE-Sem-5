from time import sleep
from picamera import PiCamera
import cv2

# Initialize camera
camera = PiCamera()
camera.resolution = (1024, 768)
camera.start_preview()
sleep(2)  # Initial camera warm-up time

# Load the face cascade classifier
face_cascade = cv2.CascadeClassifier('/home/pi/.local/lib/python3.5/site-packages/cv2/data/haarcascade_frontalface_default.xml')

try:
    while True:
        # Capture image into a temporary file
        camera.capture('foo_temp.jpg')
        
        # Load image for processing
        img = cv2.imread("foo_temp.jpg")
        gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        
        # Detect faces in the grayscale image
        faces = face_cascade.detectMultiScale(gray, 1.1, 5)
        
        # Draw rectangles around detected faces
        for (x, y, w, h) in faces:
            cv2.rectangle(img, (x, y), (x + w, y + h), (255, 0, 0), 2)
        
        # Show the image with detected faces
        cv2.imshow('Live Camera - Press "s" to Save, "q" to Quit', img)
        
        # Capture keyboard input
        key = cv2.waitKey(1) & 0xFF
        if key == ord('s'):
            # Save the image when 's' is pressed
            camera.capture('captured_image.jpg')
            print("Image saved as 'captured_image.jpg'")
        elif key == ord('q'):
            # Exit loop when 'q' is pressed
            print("Exiting...")
            break

finally:
    # Clean up
    camera.stop_preview()
    camera.close()
    cv2.destroyAllWindows()
