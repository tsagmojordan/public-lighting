import { Component } from '@angular/core';
import {Navbar} from "../../components/navbar/navbar";
import {Sidebar} from "../../components/sidebar/sidebar";

@Component({
  selector: 'app-lampadaire',
    imports: [
        Navbar,
        Sidebar
    ],
  templateUrl: './lampadaire.html',
  styleUrl: './lampadaire.css'
})
export class Lampadaire {

}
