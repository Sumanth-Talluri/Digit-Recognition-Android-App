# Digit-Recognition-Android-App

## Purpose

This is an Android Application using which you can take images of the handwritten digits and the app will predict what digit it is.

## Files

* Frontend - An Android Application which enables users to click images and upload the images to the server and then display the number predicted by the model.

* Backend - I trained a deep-learning framework from scratch on the MNIST dataset to classify different handwritten digits. Once I trained the deep learning network on the dataset I used the trained model to classify the images as they are uploaded from the application. The classified images will be stored in their respective folder on the server.

* trainModel.py - This is where I created a model and trained it

You can download the dataset from [here](https://www.tensorflow.org/datasets/catalog/mnist")
