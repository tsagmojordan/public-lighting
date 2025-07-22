import { Component } from '@angular/core';
import {Navbar} from "../../components/navbar/navbar";
import {Sidebar} from "../../components/sidebar/sidebar";

@Component({
  selector: 'app-rapport',
    imports: [
        Navbar,
        Sidebar
    ],
  templateUrl: './rapport.html',
  styleUrl: './rapport.css'
})
export class Rapport {

}
