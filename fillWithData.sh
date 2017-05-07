#!/bin/bash
curl -H "Content-Type: application/json" -X POST -d '{"firstname": "Pi","lastname": "Ko"}' http://192.168.99.100:8765/api/user
curl -H "Content-Type: application/json" -X POST -d '{"firstname": "Jo","lastname": "Ko"}' http://192.168.99.100:8765/api/user
curl -H "Content-Type: application/json" -X POST -d '{"firstname": "Ju","lastname": "Ko"}' http://192.168.99.100:8765/api/user

curl -H "Content-Type: application/json" -X POST -d '{"name": "Apple","seller": "Pi Ko","price": 1}' http://192.168.99.100:8765/api/product
curl -H "Content-Type: application/json" -X POST -d '{"name": "Orange","seller": "Jo Ko","price": 3}' http://192.168.99.100:8765/api/product
curl -H "Content-Type: application/json" -X POST -d '{"name": "Book","seller": "Ju Ko","price": 10}' http://192.168.99.100:8765/api/product

#curl -H "Content-Type: application/json" -X POST -d '{"customerId":"590f07b3b3cb1f0001473fa6","productIds":["AVviuiPAhHM68bCAk6wD","AVviuiPAhHM68bCAk6wD","AVviuiPAhHM68bCAk6wD","AVviuirVhHM68bCAk6wE"]}' http://192.168.99.100:8765/api/order