import {Component, OnInit} from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {NgOptimizedImage} from '@angular/common';

@Component({
  selector: 'app-sidebar',
  imports: [RouterLink, NgOptimizedImage, RouterLinkActive],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css',
  standalone: true
})
export class Sidebar implements OnInit{
  protected data: any;
  protected userConnected: string | undefined;

  constructor() { }

  ngOnInit(): void {

    this.data =[
    {name:"Dashboard",link:"/dashboard-minee",src:'dashboard.svg'},
    {name:"Lampadaires",link:"/lampadaire",src:'lamp.svg'},
    {name:"Consommation",link:"/consommation",src:'recycle.svg'},
    {name:"Maintenance",link:"/maintenance",src:'fix.svg'},
    {name:"Notification",link:"/notification",src:'notification.svg'},
    {name:"Rapport",link:"/rapport",src:'file.svg'},
    {name:"Prediction IA",link:"/prediction-ia",src:'brain.svg'},
  ]
    this.userConnected=this.getUserConnected();
    }

  getUserConnected(){
    return "MINEE"
  };

}
