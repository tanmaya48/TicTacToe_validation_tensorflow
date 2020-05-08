#!/usr/bin/env python
# coding: utf-8

# In[1]:



import numpy as np
import tensorflow as tf
import pandas as pd


X = pd.read_csv("TTT_train.csv")
X_t = pd.read_csv("TTT_test.csv")

X.head(10)


# In[2]:


y = X.pop('rslt')
y_t = X_t.pop('rslt')


# In[3]:


### make sure X and y are dataframe and series, numpy array does not work 

dataset = tf.data.Dataset.from_tensor_slices((X.values, y.values))

for feat, targ in dataset.take(10):
    print ('Features: {}, Target: {}'.format(feat, targ))


# In[4]:


train_dataset = dataset.shuffle(len(X)).batch(1)


# In[7]:



def get_compiled_model():
  model = tf.keras.Sequential([
      tf.keras.layers.Dense(9, activation='relu'),  ### this is input
      
    tf.keras.layers.Dense(32, activation='relu'),  ### this is custom
    tf.keras.layers.Dense(32, activation='relu'),  ### this is custom
    tf.keras.layers.Dense(12, activation='relu'),  ### this is custom
    
    tf.keras.layers.Dense(3,activation='softmax')
  ])




  ##sgd = tf.keras.optimizers.SGD(lr=0.05, momentum=0.0, decay=0.000, nesterov=False)
  model.compile(optimizer='adam',
                loss='sparse_categorical_crossentropy',
                metrics=['accuracy'])
  return model


# In[17]:


model = get_compiled_model()


##model.load_weights('TTT_w', by_name=False)

model.fit(train_dataset, epochs=50)


model.save_weights('TTT_w')


# In[18]:


val_loss, val_acc = model.evaluate(X_t, y_t)
print(val_loss)
print(val_acc)


# In[19]:


pred = model.predict_classes(X_t)  ##   model.predict(X_t) for array of output confidence

print(len(y_t))

k=0
for i in range(0,len(y_t),1):
    if(pred[i]==y_t[i]):
        print(pred[i],' ',y_t[i])
        k+=1
 



print(k)        


# In[ ]:




