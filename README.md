#MICROSERVICOS COM JAVALIN IO
_Javalin framework for lightweight microservices_

Java 8 or later and kotlin, servlet based, unopionated framework and main goas are simplicity.

- Goal: Write web applications quickly.

- Requirements:
    - no requirements for application structure;
    - no annotations;
    - no reflection;
    - no other magics;
    - just code!
        
- Author: **Rudson Kiyoshi Souza Carvalho**, SP - 27/03/2019

- Description: Simple Users CRUD
    
    - **GET** http://localhost:7000/users
    
    ```
    [
         {
             "uuid": "9f8eb100-635a-4b58-94af-62de69e141ad",
             "name": "Maria dos Santos",
             "email": "maria@test.com"
         },
         {
             "uuid": "25b23274-8b6e-49d8-9fec-26786825d290",
             "name": "Paula",
             "email": "paula@test.com"
         },
         {
             "uuid": "8b88a619-cc15-433f-9fcf-6e1d3000399c",
             "name": "Carla",
             "email": "carla@test.com"
         },
         {
             "uuid": "8900b970-8335-4413-8b7f-0dc599b440bc",
             "name": "Aretuza",
             "email": "aretuza@test.com"
         }
     ]
     ```

     - **GET** http://localhost:7000/users/9f8eb100-635a-4b58-94af-62de69e141ad
 
      ```
      {
        "uuid": "9f8eb100-635a-4b58-94af-62de69e141ad",
        "name": "Maria dos Santos",
        "email": "maria@test.com"
      }
      ```

     - **PATCH** http://localhost:7000/users/9f8eb100-635a-4b58-94af-62de69e141ad
       
       (payload)
       
      ```
      {
            "uuid": "9f8eb100-635a-4b58-94af-62de69e141ad",
            "name": "Maria dos Santos",
            "email": "maria@test.com"
      }
      ```
     - **POST** http://localhost:7000/users
        
       (payload)
       
      ```
      {           
            "name": "Eduarda",
            "email": "eduarda@test.com"
      }
      ```   
    
     - **DELETE** http://localhost:7000/users/9f8eb100-635a-4b58-94af-62de69e141ad
     
     
     