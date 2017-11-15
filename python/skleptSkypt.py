import serial, urllib, json, time
def getOrderId():
    
    url = "http://admin:123456@185.24.219.228/orders/"
    orderId = urllib.urlopen(url + "first").read()
    
    return int(orderId)

def getProducts():

    url = "http://admin:123456@185.24.219.228/orders/"
    orderId = getOrderId()
    if (orderId == -1):
        return -1
    jsonResponse = urllib.urlopen(url + "get/" + str(orderId)).read()

    data = json.loads(jsonResponse)
    
    products = orderId + ";" ## ustaw formatowanie
    
    productsList = data['products'] ##Lista produktow

    for i in productsList:
        products += str(i['product']['id']) + ":" + str(i['qty']) + ";" ##ustaw formatowanie
        
    return products

def orderSent():
    
    orderId = getOrderId()
    url = "http://admin:123456@185.24.219.228/orders/setStatus/" + str(orderId)
    data = urllib.urlencode({"status":"Sent"}) ## ewentualnie mozna zmienic statusy
    return urllib.urlopen(url, data).read()

if (name == "__main__"):
    ard = serial.Serial('/dev/tty.usbserial', 9600)
    while True:
        if (getOrderId() != -1):
            ard.write (getProducts())
            while (ard.readline() != "done"):
                continue
            orderSent()
            sleep(10)
            
            
                
            
            
        
        
        
        