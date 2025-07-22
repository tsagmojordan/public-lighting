import {Component, Input, OnInit} from '@angular/core';
import { RouterLink } from '@angular/router';
import {TerritoryService} from '../../service/territory-service';
import {FormsModule} from '@angular/forms';
import {SharedService} from '../../service/shared-service';


@Component({
  selector: 'app-navbar',
  imports: [FormsModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})

export class Navbar implements OnInit{


  selectedLanguage: string = 'Francais'; // Default to French
  title: string = 'Dashboard National';
  subtitle: string = 'Systeme de management des éclairages publics-République du Cameroun';
  regions: { id: number; nom: string }[] = [];
  departments: { id: number; nom: string }[] = [];
  arrondissements: { id: number; nom: string }[] = [];
  selectedRegion: string = "Region";
  selectedDepartment: string  = "Departement";
  selectedArrondissement: string = "Arrondissement";
  @Input() region:string="Region";


  ngOnInit(): void {
    this.region="National"
    this.regions = this.adminDivisions.getRegion();

  }

  constructor(private adminDivisions: TerritoryService,private shareService: SharedService) { }
  onLanguageChange(language: string): void {
    this.selectedLanguage = language;
    this.shareService.setLanguage(this.selectedLanguage)

  }

  onRegionChange(regionName: string): void {
    this.selectedRegion = regionName;
    this.selectedDepartment = "Departement";
    this.selectedArrondissement = "Arrondissement";
    this.departments = this.adminDivisions.getDepartmentByRegion(regionName);
    this.arrondissements = [];
    this.shareService.setRegion(this.selectedRegion)
  }

  onDepartmentChange(departmentName: string): void {
    this.selectedDepartment = departmentName;
    this.selectedArrondissement = "Arrondissement";
    this.arrondissements = this.adminDivisions.getArrondissementByDepartement(departmentName);
    this.shareService.setDepartement(this.selectedDepartment)
  }

  onArrondissementChange(arrondissementName: string): void {
    this.selectedArrondissement = arrondissementName;
    this.shareService.setArrondissement(this.selectedArrondissement)
  }

}
