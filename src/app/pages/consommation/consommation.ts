
// dashboard.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Sidebar} from '../../components/sidebar/sidebar';
import {Navbar} from '../../components/navbar/navbar';
import {Box} from '../../components/box/box';

@Component({
  selector: 'app-consommation',
  standalone: true,
  imports: [CommonModule, Sidebar, Navbar, Box],
  templateUrl: './consommation.html',
  styleUrls: ['./consommation.css']
})
export class Consommation implements OnInit {
  // Données pour les boxes
  interventionsUrgentes = {
    total: '567,890',
    unit: 'kWh',
    icon: 'flash.svg',
    color: 'yellow'
  };

  maintenanceProgrammee = {
    total: '34,567,890',
    unit: 'FCFA',
    icon: 'calendar.svg',
    color: 'blue'
  };

  interventionsCompletees = {
    total: '8,765,432',
    unit: 'FCFA économisés',
    icon: 'puzzle.svg',
    color: 'green'
  };

  tempsMoyenReparation = {
    total: '87.3%',
    unit: 'Performance',
    icon: 'chart.svg',
    color: 'purple'
  };

  // Données pour le graphique Consommation par Tranche Horaire
  consommationData = [
    { region: 'Centre', value: 147000, color: '#8b5cf6' },
    { region: 'Littoral', value: 125000, color: '#7c3aed' },
    { region: 'Ouest', value: 95000, color: '#ec4899' },
    { region: 'Nord', value: 75000, color: '#ef4444' },
    { region: 'Extrême-Nord', value: 85000, color: '#3b82f6' },
    { region: 'Adamaoua', value: 65000, color: '#06b6d4' },
    { region: 'Nord-Ouest', value: 78000, color: '#10b981' },
    { region: 'Sud-Ouest', value: 70000, color: '#14b8a6' },
    { region: 'Est', value: 55000, color: '#fef08a' },
    { region: 'Sud', value: 45000, color: '#fb923c' }
  ];

  maxConsommation = 160000;

  // Données pour le graphique Types de Pannes
  pannesData = [
    { hour: '18h', value: 45 },
    { hour: '19h', value: 58 },
    { hour: '20h', value: 70 },
    { hour: '21h', value: 68 },
    { hour: '22h', value: 62 },
    { hour: '23h', value: 58 },
    { hour: '00h', value: 48 },
    { hour: '01h', value: 42 },
    { hour: '02h', value: 38 },
    { hour: '03h', value: 35 },
    { hour: '04h', value: 40 },
    { hour: '05h', value: 30 }
  ];

  maxPannes = 70;
  svgPath = '';

  ngOnInit() {
    this.generateSVGPath();
  }

  getBarHeight(value: number): number {
    return (value / this.maxConsommation) * 100;
  }

  generateSVGPath() {
    const width = 400;
    const height = 200;
    const padding = 40;
    const dataLength = this.pannesData.length;

    const xStep = (width - padding * 2) / (dataLength - 1);
    const yScale = (height - padding * 2) / this.maxPannes;

    let path = '';
    this.pannesData.forEach((point, index) => {
      const x = padding + index * xStep;
      const y = height - padding - (point.value * yScale);

      if (index === 0) {
        path += `M ${x} ${y}`;
      } else {
        path += ` L ${x} ${y}`;
      }
    });

    this.svgPath = path;
  }
}
