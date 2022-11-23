import base64
import os
import shutil
from datetime import datetime
from flask import Flask, request
from numpy import argmax
import numpy as np
from keras_preprocessing.image import load_img
from keras_preprocessing.image import img_to_array
from keras.models import load_model


app = Flask(__name__)
app.config['STORAGE'] = os.path.join(os.path.join(os.getcwd(),'..'),'ImageData')


def load_image(filename):

    img = load_img(filename,color_mode="grayscale", target_size=(28, 28))
    img = img_to_array(img)
    
    white_count = (img >= 128).sum()
    black_count = (img <= 128).sum()

    if (black_count < white_count):
        img = np.where(img >= 128, 0, 255)
    else:
        img = np.where(img >= 128, 255, 0)
    
    img = img.reshape(1, 28, 28, 1)
    
    img = img.astype('float32')
    img = img / 255.0

    return img


def predict_number(filepath):

    img = load_image(filepath)

    model = load_model('cnn_model.h5')

    predict_value = model.predict(img)
    digit = argmax(predict_value)
    return digit


def create_directory(directory):
    try:
        if not os.path.exists(directory):
            os.mkdir(directory)
    except Exception as error:
        raise Exception(error)


def convert_and_save(file_path,b64_string):

    try:
        with open(file_path, "wb") as fh:
            fh.write(base64.decodebytes(b64_string.encode()))
    except Exception as error:
        raise Exception(error)

def delete_file(filepath):
    if os.path.exists(filepath):
        os.remove(filepath)
    else:
        print("File doesn't exist.")

@app.route('/', methods=['POST'])
def upload_file():

    try:
        base64_image_string = request.form['imageString']
        date_string = datetime.now().strftime("%d-%m-%Y_%H_%M_%S")
        file_name = "image-{}.jpg".format(date_string)
        file_directory = os.path.join(app.config['STORAGE'], "Temp")
        create_directory(file_directory)
        file_directory = os.path.join(file_directory,file_name)
        convert_and_save(file_directory,base64_image_string)
        predicted_number = predict_number(file_directory)
        print(predicted_number)
        output_directory = os.path.join(app.config['STORAGE'], str(predicted_number))
        create_directory(output_directory)
        shutil.copy(file_directory,output_directory)
        delete_file(file_directory)
        return str(predicted_number),200
    except Exception as error:
        print(error)
        return "Upload Failed",400




if __name__ == '__main__':
    app.run(debug=True)
