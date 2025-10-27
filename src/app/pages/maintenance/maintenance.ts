// maintenance.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {Sidebar} from '../../components/sidebar/sidebar';
import {Navbar} from '../../components/navbar/navbar';
import {Box} from '../../components/box/box';

interface MaintenanceCommune {
  commune: string;
  effectuees: number;
  enCours: number;
  planifiees: number;
  urgentes: number;
  tempsLatence: number; // en heures
  tauxReussite: number; // en pourcentage
  color: string;
}

interface MaintenanceType {
  type: string;
  count: number;
  pourcentage: number;
  color: string;
}

interface TempsLatenceData {
  periode: string;
  moyenne: number;
  objectif: number;
}

@Component({
  selector: 'app-maintenance',
  standalone: true,
  imports: [CommonModule, FormsModule, Sidebar, Navbar, Box],
  templateUrl: './maintenance.html',
  styleUrls: ['./maintenance.css']
})
export class Maintenance implements OnInit {
  // Données pour les stats cards
  totalMaintenances = {
    total: '12,450',
    unit: 'Interventions',
    icon: 'wrench.svg',
    color: 'blue'
  };

  maintenancesUrgentes = {
    total: '342',
    unit: 'En attente',
    icon: 'alert.svg',
    color: 'red'
  };

  tempsLatenceMoyen = {
    total: '4.2h',
    unit: 'Temps moyen',
    icon: 'clock.svg',
    color: 'orange'
  };

  tauxReussite = {
    total: '94.5%',
    unit: 'Taux de succès',
    icon: 'check.svg',
    color: 'green'
  };

  // Filtre période
  selectedPeriode: string = 'mois';
  selectedCommune: string = '';

  // Données maintenances par commune
  maintenancesParCommune: MaintenanceCommune[] = [
    { commune: 'Yaoundé I', effectuees: 1250, enCours: 45, planifiees: 120, urgentes: 28, tempsLatence: 3.5, tauxReussite: 96, color: '#8b5cf6' },
    { commune: 'Yaoundé II', effectuees: 980, enCours: 32, planifiees: 95, urgentes: 15, tempsLatence: 4.2, tauxReussite: 94, color: '#7c3aed' },
    { commune: 'Yaoundé III', effectuees: 1120, enCours: 38, planifiees: 110, urgentes: 22, tempsLatence: 3.8, tauxReussite: 95, color: '#ec4899' },
    { commune: 'Douala I', effectuees: 1350, enCours: 52, planifiees: 135, urgentes: 35, tempsLatence: 5.1, tauxReussite: 92, color: '#ef4444' },
    { commune: 'Douala II', effectuees: 1180, enCours: 41, planifiees: 115, urgentes: 25, tempsLatence: 4.5, tauxReussite: 93, color: '#3b82f6' },
    { commune: 'Douala III', effectuees: 890, enCours: 28, planifiees: 85, urgentes: 18, tempsLatence: 4.8, tauxReussite: 91, color: '#06b6d4' },
    { commune: 'Bafoussam', effectuees: 650, enCours: 22, planifiees: 65, urgentes: 12, tempsLatence: 5.5, tauxReussite: 89, color: '#10b981' },
    { commune: 'Garoua', effectuees: 720, enCours: 25, planifiees: 70, urgentes: 14, tempsLatence: 6.2, tauxReussite: 88, color: '#14b8a6' },
    { commune: 'Bamenda', effectuees: 580, enCours: 18, planifiees: 55, urgentes: 10, tempsLatence: 5.8, tauxReussite: 90, color: '#f59e0b' },
    { commune: 'Maroua', effectuees: 490, enCours: 15, planifiees: 48, urgentes: 8, tempsLatence: 6.5, tauxReussite: 87, color: '#fb923c' }
  ];

  filteredMaintenances: MaintenanceCommune[] = [];

  // Types de maintenance
  typesMaintenances: MaintenanceType[] = [
    { type: 'Préventive', count: 5680, pourcentage: 45.6, color: '#10b981' },
    { type: 'Corrective', count: 3420, pourcentage: 27.5, color: '#ef4444' },
    { type: 'Urgente', count: 1890, pourcentage: 15.2, color: '#f59e0b' },
    { type: 'Programmée', count: 1460, pourcentage: 11.7, color: '#3b82f6' }
  ];

  // Temps de latence par période
  latenceData: TempsLatenceData[] = [
    { periode: 'Jan', moyenne: 5.2, objectif: 4 },
    { periode: 'Fév', moyenne: 4.8, objectif: 4 },
    { periode: 'Mar', moyenne: 4.5, objectif: 4 },
    { periode: 'Avr', moyenne: 4.2, objectif: 4 },
    { periode: 'Mai', moyenne: 3.9, objectif: 4 },
    { periode: 'Jun', moyenne: 4.1, objectif: 4 },
    { periode: 'Jul', moyenne: 4.3, objectif: 4 },
    { periode: 'Aoû', moyenne: 4.6, objectif: 4 },
    { periode: 'Sep', moyenne: 4.4, objectif: 4 },
    { periode: 'Oct', moyenne: 4.0, objectif: 4 }
  ];

  maxLatence = 6;

  // Communes pour le filtre
  communes: string[] = [];

  ngOnInit() {
    this.filteredMaintenances = [...this.maintenancesParCommune];
    this.communes = ['Toutes', ...this.maintenancesParCommune.map(m => m.commune)];
  }

  filterByCommune() {
    if (!this.selectedCommune || this.selectedCommune === 'Toutes') {
      this.filteredMaintenances = [...this.maintenancesParCommune];
    } else {
      this.filteredMaintenances = this.maintenancesParCommune.filter(
        m => m.commune === this.selectedCommune
      );
    }
  }

  getBarHeight(value: number, max: number): number {
    return (value / max) * 100;
  }

  getMaxEffectuees(): number {
    return Math.max(...this.maintenancesParCommune.map(m => m.effectuees));
  }

  getCommuneStatus(latence: number): string {
    if (latence <= 4) return 'excellent';
    if (latence <= 5) return 'bon';
    if (latence <= 6) return 'moyen';
    return 'critique';
  }

  exporterRapport() {
    console.log('Export du rapport de maintenance');
    alert('Rapport de maintenance exporté avec succès');
  }

  planifierMaintenance() {
    console.log('Planifier une nouvelle maintenance');
    alert('Interface de planification à implémenter');
  }

  voirAlertes() {
    console.log('Voir toutes les alertes');
    alert('Liste des alertes à implémenter');
  }
}
