import { Injectable } from '@angular/core';
import { Client, Message } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private stompClient: Client;

  constructor() {
    // Initialize the Stomp client
    this.stompClient = new Client({
      brokerURL: 'ws://localhost:8080/gs-guide-websocket', // Your WebSocket server URL
      debug: (str) => {
        console.log(str);
      },
    });

    this.stompClient.onConnect = () => {
      console.log('WebSocket connected');
      this.subscribeToTopic('/topic/greetings'); // Subscribe to a topic
    };
  }

  connect(): void {
    this.stompClient.activate();
  }

  disconnect(): void {
    this.stompClient.deactivate();
  }

  subscribeToTopic(topic: string): void {
    this.stompClient.subscribe(topic, (message: Message) => {
      console.log('Received message:', message.body);
      // Handle incoming messages here
    });
  }

  sendMessage(topic: string, message: string): void {
    this.stompClient.publish({
      destination: topic,
      body: message,
    });
  }
}
