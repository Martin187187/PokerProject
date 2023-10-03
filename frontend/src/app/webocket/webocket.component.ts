import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../websocket.service';

@Component({
  selector: 'app-webocket',
  templateUrl: './webocket.component.html',
  styleUrls: ['./webocket.component.css']
})
export class WebSocketComponent implements OnInit {
  constructor(private webSocketService: WebSocketService) {}

  ngOnInit(): void {
    this.webSocketService.connect();
  }

  ngOnDestroy(): void {
    this.webSocketService.disconnect();
  }

  sendMessage(): void {
    this.webSocketService.sendMessage('/app/hello', 'Hello, WebSocket!');
  }
}