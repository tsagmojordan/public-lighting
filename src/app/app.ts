import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {Sidebar} from './components/sidebar/sidebar';
import {DasbordMinee} from './pages/dasbord-minee/dasbord-minee';


@Component({
  selector: 'app-root',
  imports: [DasbordMinee, RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'web-app';
}
