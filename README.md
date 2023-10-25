# Deutsche Bank - Merchant Solutions

The application is implemented using OpenJDK 20, Spring boot 3.1.5. 
Signals can be added using the API and as of now they are stored in a Map(in-memory). This could be extended to be saved 
in a persistent storage. 

Steps to create signals and perform tasks
1. Add a signal using the API from any rest client such as Postman or curl as
   curl --location 'http://localhost:8080/signal/add' \
   --header 'Content-Type: application/json' \
   --header 'Cookie: Idea-65d326be=08f50d50-997c-47d2-8111-95a614e27ed2' \
   --data '{
   "signalId":1,
   "ops":["setUp","setAlgoParam(1, 60)","performCalc", "submitToMarket"]
   }'

   curl --location 'http://localhost:8080/signal/add' \
   --header 'Content-Type: application/json' \
   --header 'Cookie: Idea-65d326be=08f50d50-997c-47d2-8111-95a614e27ed2' \
   --data '{
   "signalId":2,
   "ops":["reverse","setAlgoParam(1, 80)","submitToMarket"]
   }'

   curl --location 'http://localhost:8080/signal/add' \
   --header 'Content-Type: application/json' \
   --header 'Cookie: Idea-65d326be=08f50d50-997c-47d2-8111-95a614e27ed2' \
   --data '{
   "signalId":3,
   "ops":["setAlgoParam(1, 90)","setAlgoParam(2, 15)","performCalc", "submitToMarket"]
   }'

   curl --location 'http://localhost:8080/signal/add' \
   --header 'Content-Type: application/json' \
   --header 'Cookie: Idea-65d326be=08f50d50-997c-47d2-8111-95a614e27ed2' \
   --data '{
   "signalId":6,
   "ops":["setAlgoParam(1,90000)","setAlgoParam(5, 15)", "submitToMarket"]
   }'
2. Confirm 4 signals are created by triggering
   curl --location 'http://localhost:8080/signal/all' \
   --header 'Cookie: Idea-65d326be=08f50d50-997c-47d2-8111-95a614e27ed2'
3. Invoke a signal as
   curl --location --request POST 'http://localhost:8080/signal/1' \
   --header 'Cookie: Idea-65d326be=08f50d50-997c-47d2-8111-95a614e27ed2'

    In the console, the Algo operations will be printed for the specified signal. 

    