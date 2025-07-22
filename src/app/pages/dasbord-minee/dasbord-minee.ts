import {Component, Input} from '@angular/core';
import {Navbar} from '../../components/navbar/navbar';
import {RouterOutlet} from '@angular/router';
import {Sidebar} from '../../components/sidebar/sidebar';
import {Overview} from '../overview/overview';
import {Box} from '../../components/box/box';
import {NgStyle} from '@angular/common';
import {LinearChart} from '../../components/linear-chart/linear-chart';
import {ColumChart} from '../../components/colum-chart/colum-chart';

@Component({
  selector: 'app-dasbord-minee',
  imports: [
    Navbar,
    Sidebar,
    RouterOutlet,
    Overview,
    Box,
    NgStyle,
    LinearChart,
    ColumChart
  ],
  templateUrl: './dasbord-minee.html',
  styleUrl: './dasbord-minee.css',
  standalone: true
})
export class DasbordMinee {
  @Input() actifStreetLight:number=1500000;
  @Input() brokenDownStreetLight:number=4000;
  @Input() powerSaved:number=3240;
  @Input() streetLight:number=1504000;
  @Input() indiceStreetLight:number=+1;
  @Input() indiceActifStreetLight:number=+2.3;
  @Input() indiceBrokenDownStreetLight:number=-1.4;
  @Input() indicePowerSaved:number=+2.5;



}
