import { Component } from '@angular/core';
import {Navbar} from "../../components/navbar/navbar";
import {Sidebar} from "../../components/sidebar/sidebar";

@Component({
  selector: 'app-maintenance',
    imports: [
        Navbar,
        Sidebar
    ],
  templateUrl: './maintenance.html',
  styleUrl: './maintenance.css'
})
export class Maintenance {

}
