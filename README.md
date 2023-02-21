# Digit-Recognition-Android-App

## Introduction

This is an Android Application using which you can take images of the handwritten digits and the app will predict what digit it is.

## Files

* Frontend - An Android Application which enables users to click images and upload the images to the server and then display the number predicted by the model.

* Backend - I trained a deep-learning framework from scratch on the MNIST dataset to classify different handwritten digits. Once I trained the deep learning network on the dataset I used the trained model to classify the images as they are uploaded from the application. The classified images will be stored in their respective folder on the server.

* trainModel.py - This is where I created a model and trained it

* cnn_model.h5 - This is the model I got after training. This will be used to predict.

You can download the dataset from [here](https://www.tensorflow.org/datasets/catalog/mnist")

## Application Workflow

1. User opens the app CloudIt
When the user opens the app for the first time, a pop-up requesting camera access will
appear on the screen, when the user agrees, the Main Activity page will be opened.
2. In the Main Activity Page, the user can click on the Capture Image button to capture the
hand written digit image.
3. When the user clicks the capture image button, the Camera page will be opened where the
user has an option to take a photo.
When the image has been captured by the camera, the user can:
    * proceed to upload the image by clicking on the Tick button (✔).
    * proceed to retake the image by clicking on the retake symbol (↩).
4. When the User proceeds to upload the image, a new activity, View Page Activity, will be
opened where he can view the image and then upload the image.
5. In the Back end an MNIST data set is trained to classify the different handwritten digit
images that are captured.
6. The classified images will then be stored in the appropriate folder on the server.
7. Based on the status from the backend:
    * When the image is uploaded successfully, the user will be redirected to Main
Activity Page and will also be notified the digit in the image by a pop-up.
    * When the image is not uploaded, an error message, “Oops!! Upload failed
due to technical issues” will be displayed.

<br>

<img alt="Application Workflow Diagram" src="./Workflow Diagram.png"  width="60%" height="60%">

<br>

## Technical Details

1. The dimensions of the image on which the model is trained are (28,28) pixels, so we had
to reshape the incoming image to this pixel range.
2. Since the model is trained on the MNIST dataset which consists of images with black
background, we implemented in such a way that if the incoming image has white
background with black text, even then our models predicts it accurately. We acheived
this by flipping the pixel values of the image in case the white pixel count exceeds black
pixel count.
3. The type of Deep Learning framework we used is Convolutional Neural Network (CNN)
4. The accuracy of the model is 99.09
5. The classified images are being stored in the respective folders.
6. The accuracy of the model we found by the testing it on the hand written digit images
which we created was about 96.67 (29/30)
## Commands

- Enable developer options and then turn on USB Debugging on the android device
- Use these commands to run Android app on phone instead of virtual emulator on android studio
> adb devices

> abd reverse tcp:5000 tcp:5000
