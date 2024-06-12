# Active-mq task
This project was created on 2024-06-07 from a template.

All modules are separate java applications.
All configure for default activemq port tcp://localhost:61616

java -jar active-mq-task-*-1.0.0-jar-with-dependencies.jar 

## Task - Pub/Sub 

active-mq-task-publisher

active-mq-task-subscriber

Point-to-point interaction between two applications.
Subscriber can be run with "durable" argument to make it durable subscriber.

## Task - Request/Reply

active-mq-task-requestor

active-mq-task-replier

Normal request and reply interaction between two applications.

## Task - Subscriber scaling on Pub/Sub

active-mq-task-virtual-publisher

active-me-task-virtual-subscriber

Publishing messages to virtual topic, load balances them among n subscribers connected to the same queue (argument provided at start).
Subscriber needs to be run with argument, which will be included in queue name.
 


