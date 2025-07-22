import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Streelight {

  constructor() { }
  getNumberOfStreetLight(region:string,departement:string,arrondissement:string):Number{
    return 155000;
  }

  getNumberOfBrokenDownStreetLight(region:string,departement:string,arrondissement:string):Number{
    return 5000;
  }



}
