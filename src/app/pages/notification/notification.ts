import { Component } from '@angular/core';
import {Navbar} from "../../components/navbar/navbar";
import {Sidebar} from "../../components/sidebar/sidebar";

@Component({
  selector: 'app-notification',
    imports: [
        Navbar,
        Sidebar
    ],
  templateUrl: './notification.html',
  styleUrl: './notification.css'
})
export class Notification {

}
