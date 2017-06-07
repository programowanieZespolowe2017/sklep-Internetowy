
#include <LiquidCrystal.h> //DoÄąâ€šĂ„â€¦czenie bilbioteki
LiquidCrystal lcd(2, 3, 4, 5, 6, 7); //Informacja o podÄąâ€šĂ„â€¦czeniu nowego wyÄąâ€şwietlacza
int pushButton2 = 8;
int pushButton1 = 9;
int pushButton3 = 10;
byte serialchars[100];
int ilosc;
int j;
int k;
int s;
int zmienna=0;
byte message[16][2];
byte products[100];
//byte managedProducts[10][10];
int flag = 0;
int indexes[20];
int down = 0;
int up = 1;
int sredniks =0;

void button2action()
{
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("Oczekuje na");
  lcd.setCursor(0, 1);
  lcd.print("zamowienia ...");
  Serial.begin(9600);
  delay(100);
  Serial.println("done");
  flag = 0;
  j = 0;
  delay(500);

}

void setup() {

  pinMode(pushButton1, INPUT_PULLUP);
  pinMode(pushButton2, INPUT_PULLUP);
  pinMode(pushButton3, INPUT_PULLUP);
  lcd.begin(16, 2);
  button2action();
}

void button1action()
{
  if (down > 0)
  {
  down -= 1;
  up -= 1;
  delay(100);
  }
}




void button3action()
{
  if ( up+1 <sredniks)
  {
  down += 1;
  up += 1;
  delay(100);
  }
}
//
//void manageProducts()
//{
//  int i = 0;
//  s = 0;
//  int y = 0;
//  for (i; i < 100; i++)
//  {
//    if (products[i] == ';')
//    {
//      s++;
//      y = 0;
//    }
//    else if (products[i] == ':')
//    {
//      managedProducts[y][s] = ' ';
//      y++;
//      managedProducts[y][s] = 'x';
//    }
//    else
//    {
//      managedProducts[y][s] = products[i];
//    }
//    y++;
//  }
//}

void manageData()
{
  int i = 0;
  j = 0;
  k = 0;
  //  Serial.println(ilosc);
  for (i; i < ilosc; i++)
  {
    if (serialchars[i] != ';')
    {
      if (serialchars[i] != '-')
      {
        message[j][0] = serialchars[i];
        j++;
      }
    }
    if (serialchars[i] == ';')
    {
      break;
    }
  }
  Serial.println(ilosc);
  for (i; i < ilosc; i++)
  {
    products[k] = char(serialchars[i]);

  }
}


void showOrders()
{

  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.write("ZAM ");
  for (int i = 0; i < j; i++)
  {
    lcd.write(message[i][0]);
  }
  lcd.setCursor(0, 1);
  lcd.write("PID ");
  //  Serial.println(one.length());
  for (int i = indexes[down]; i < indexes[up]; i++)
  { 

    if (serialchars[i] == ':')
    {
      lcd.write(" x");
    }
    else if (serialchars[i] != ';')
    {
    lcd.write(serialchars[i]);
    }
  }
  delay(100);

}

void getIndexes()
{
  int idx = 0;
  for(int i = 0; i < 100; i++)
  if (serialchars[i] == ';')
  {
     indexes[idx] = i;
     idx++;
     sredniks+=1;
  }
}

void serialEvent()
{
  delay(300);
  ilosc = Serial.available();
  for (int i = 0; i < ilosc; i++) {
    serialchars[i] = Serial.read();
  }
  manageData();
  delay(250);
  getIndexes();
  //  Serial.end();
  flag = 1;
}




void loop() {
  int buttonState1 = digitalRead(pushButton1);//clear
  int buttonState2 = digitalRead(pushButton2);//up
  int buttonState3 = digitalRead(pushButton3);//down

  if (buttonState1 == LOW)
  {
    button1action();
  }

  if (buttonState3 == LOW)
  {
    button3action();
  }

  if (buttonState2 == LOW)
  {
    button2action();
  }
  if (flag == 1)
  {
    showOrders();
  }

}


