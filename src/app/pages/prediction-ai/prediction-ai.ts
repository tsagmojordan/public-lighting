import {Component, Input} from '@angular/core';
import {Navbar} from "../../components/navbar/navbar";
import {Sidebar} from "../../components/sidebar/sidebar";
import {Box} from '../../components/box/box';

@Component({
  selector: 'app-prediction-ai',
  imports: [
    Navbar,
    Sidebar,
    Box
  ],
  templateUrl: './prediction-ai.html',
  styleUrl: './prediction-ai.css'
})
export class PredictionAi {

  @Input() totalPrediction:number=1350;
  @Input() avoidBrokenDown: number=310;
  @Input() moneySaved: number=3240;
  @Input() aiPrecision:number=67;
  @Input() indiceStreetLight:number=+1;
  @Input() indiceActifStreetLight:number=+2.3;
  @Input() indiceBrokenDownStreetLight:number=-1.4;
  @Input() indicePowerSaved:number=+2.5;



}
