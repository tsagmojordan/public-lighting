import {Component, Input, input, numberAttribute, OnInit} from '@angular/core';
import {NgStyle} from '@angular/common';

@Component({
  selector: 'app-box',
  imports: [
    NgStyle
  ],
  templateUrl: './box.html',
  styleUrl: './box.css'
})
export class Box implements OnInit {
  @Input()  title: string="box-title";
  @Input()  icon: string="";
  @Input({transform: numberAttribute})  percent:number=0;
  @Input() printedPercent:string="0";
  @Input({transform: numberAttribute})  total:number=0;
  @Input() color:string='green';
  @Input() period: string='mois';
  indiceColor: string="green";



  ngOnInit(): void {
    this.changeColor();
  }
  changeColor(){
    if (this.percent<0){
      this.indiceColor="red";
    }else{
    //  this.printedPercent=this.formatNumber(this.percent)
      this.indiceColor="green";

    }
  }
  formatNumber(value: number): string {
    return value > 0 ? `+${value}` : `${value}`;
  }



}
