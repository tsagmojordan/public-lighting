import { Component } from '@angular/core';
import {Navbar} from "../../components/navbar/navbar";
import {Sidebar} from "../../components/sidebar/sidebar";

@Component({
  selector: 'app-consommation',
    imports: [
        Navbar,
        Sidebar
    ],
  templateUrl: './consommation.html',
  styleUrl: './consommation.css'
})
export class Consommation {

}
