import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {Sidebar} from '../../components/sidebar/sidebar';
import {Navbar} from '../../components/navbar/navbar';

interface Notification {
  id: string;
  type: 'urgente' | 'maintenance' | 'info' | 'systeme';
  titre: string;
  message: string;
  localisation: string;
  region: string;
  date: string;
  heure: string;
  lue: boolean;
  priorite: 'haute' | 'moyenne' | 'basse';
  lampadaireId?: string;
}

@Component({
  selector: 'app-notifications',
  standalone: true,
  imports: [CommonModule, FormsModule, Sidebar, Navbar],
  templateUrl: './notification.html',
  styleUrls: ['./notification.css']
})
export class Notifications implements OnInit {
  notifications: Notification[] = [];
  filteredNotifications: Notification[] = [];

  // Filtres
  selectedType: string = 'all';
  selectedPriorite: string = 'all';
  selectedStatut: string = 'all';
  searchTerm: string = '';

  // Stats
  totalNotifications: number = 0;
  nonLues: number = 0;
  urgentes: number = 0;
  aujourdhui: number = 0;

  ngOnInit() {
    this.loadNotifications();
    this.calculateStats();
    this.applyFilters();
  }

  loadNotifications() {
    this.notifications = [
      {
        id: 'NOT-001',
        type: 'urgente',
        titre: 'Panne Critique Détectée',
        message: 'Panne électrique sur 15 lampadaires consécutifs. Intervention immédiate requise.',
        localisation: 'Avenue Kennedy, Yaoundé',
        region: 'Centre',
        date: '27/10/2025',
        heure: '08:30',
        lue: false,
        priorite: 'haute',
        lampadaireId: 'LP-YDE-001'
      },
      {
        id: 'NOT-002',
        type: 'maintenance',
        titre: 'Maintenance Programmée',
        message: 'Maintenance préventive planifiée pour 25 lampadaires du secteur.',
        localisation: 'Boulevard de la Liberté, Douala',
        region: 'Littoral',
        date: '27/10/2025',
        heure: '07:15',
        lue: false,
        priorite: 'moyenne',
        lampadaireId: 'LP-DLA-045'
      },
      {
        id: 'NOT-003',
        type: 'urgente',
        titre: 'Surconsommation Anormale',
        message: 'Consommation 250% supérieure à la normale détectée. Vérification nécessaire.',
        localisation: 'Rue Commerciale, Bafoussam',
        region: 'Ouest',
        date: '27/10/2025',
        heure: '06:45',
        lue: true,
        priorite: 'haute',
        lampadaireId: 'LP-BFS-023'
      },
      {
        id: 'NOT-004',
        type: 'info',
        titre: 'Maintenance Terminée',
        message: 'Intervention de maintenance complétée avec succès. Tous les lampadaires sont opérationnels.',
        localisation: 'Place de la Réunification, Garoua',
        region: 'Nord',
        date: '26/10/2025',
        heure: '18:20',
        lue: true,
        priorite: 'basse',
        lampadaireId: 'LP-GAR-089'
      },
      {
        id: 'NOT-005',
        type: 'systeme',
        titre: 'Mise à Jour Système',
        message: 'Nouvelle version du système de monitoring disponible. Mise à jour recommandée.',
        localisation: 'Système Central',
        region: 'National',
        date: '26/10/2025',
        heure: '14:00',
        lue: true,
        priorite: 'basse'
      },
      {
        id: 'NOT-006',
        type: 'urgente',
        titre: 'Défaillance Matérielle',
        message: 'Dysfonctionnement du transformateur. 8 lampadaires affectés.',
        localisation: 'Avenue des Palmiers, Bamenda',
        region: 'Nord-Ouest',
        date: '26/10/2025',
        heure: '11:30',
        lue: false,
        priorite: 'haute',
        lampadaireId: 'LP-BAM-056'
      },
      {
        id: 'NOT-007',
        type: 'maintenance',
        titre: 'Inspection Mensuelle',
        message: 'Inspection de routine prévue pour 50 lampadaires de la zone.',
        localisation: 'Quartier Administratif, Maroua',
        region: 'Extrême-Nord',
        date: '26/10/2025',
        heure: '09:00',
        lue: true,
        priorite: 'moyenne',
        lampadaireId: 'LP-MAR-012'
      },
      {
        id: 'NOT-008',
        type: 'info',
        titre: 'Rapport Hebdomadaire',
        message: 'Le rapport de performance de la semaine est maintenant disponible.',
        localisation: 'Système Central',
        region: 'National',
        date: '25/10/2025',
        heure: '16:45',
        lue: true,
        priorite: 'basse'
      },
      {
        id: 'NOT-009',
        type: 'urgente',
        titre: 'Câble Endommagé',
        message: 'Câble principal sectionné suite à des travaux. 12 lampadaires hors service.',
        localisation: 'Route Nationale, Bertoua',
        region: 'Est',
        date: '25/10/2025',
        heure: '13:20',
        lue: false,
        priorite: 'haute',
        lampadaireId: 'LP-BER-078'
      },
      {
        id: 'NOT-010',
        type: 'maintenance',
        titre: 'Remplacement LED',
        message: 'Remplacement programmé de 30 LED en fin de vie.',
        localisation: 'Centre-Ville, Ebolowa',
        region: 'Sud',
        date: '25/10/2025',
        heure: '10:00',
        lue: true,
        priorite: 'moyenne',
        lampadaireId: 'LP-EBO-034'
      }
    ];
  }

  calculateStats() {
    this.totalNotifications = this.notifications.length;
    this.nonLues = this.notifications.filter(n => !n.lue).length;
    this.urgentes = this.notifications.filter(n => n.type === 'urgente').length;

    const today = '27/10/2025'; // Date actuelle mockée
    this.aujourdhui = this.notifications.filter(n => n.date === today).length;
  }

  applyFilters() {
    this.filteredNotifications = this.notifications.filter(notification => {
      const matchType = this.selectedType === 'all' || notification.type === this.selectedType;
      const matchPriorite = this.selectedPriorite === 'all' || notification.priorite === this.selectedPriorite;
      const matchStatut = this.selectedStatut === 'all' ||
        (this.selectedStatut === 'lues' && notification.lue) ||
        (this.selectedStatut === 'nonlues' && !notification.lue);
      const matchSearch = this.searchTerm === '' ||
        notification.titre.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        notification.message.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        notification.localisation.toLowerCase().includes(this.searchTerm.toLowerCase());

      return matchType && matchPriorite && matchStatut && matchSearch;
    });
  }

  onFilterChange() {
    this.applyFilters();
  }

  marquerCommeLue(notification: Notification) {
    notification.lue = true;
    this.calculateStats();
  }

  marquerCommeNonLue(notification: Notification) {
    notification.lue = false;
    this.calculateStats();
  }

  supprimerNotification(id: string) {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette notification ?')) {
      this.notifications = this.notifications.filter(n => n.id !== id);
      this.calculateStats();
      this.applyFilters();
    }
  }

  marquerToutCommeLu() {
    this.notifications.forEach(n => n.lue = true);
    this.calculateStats();
    this.applyFilters();
  }

  public supprimerLues() {
    const nbLues = this.notifications.filter(n => n.lue).length;
    if (nbLues === 0) {
      alert('Aucune notification lue à supprimer.');
      return;
    }

    if (confirm(`Êtes-vous sûr de vouloir supprimer ${nbLues} notification(s) lue(s) ?`)) {
      this.notifications = this.notifications.filter(n => !n.lue);
      this.calculateStats();
      this.applyFilters();
    }
  }

  getTypeClass(type: string): string {
    const classes: { [key: string]: string } = {
      'urgente': 'type-badge-urgente',
      'maintenance': 'type-badge-maintenance',
      'info': 'type-badge-info',
      'systeme': 'type-badge-systeme'
    };
    return classes[type] || '';
  }

  getPrioriteClass(priorite: string): string {
    const classes: { [key: string]: string } = {
      'haute': 'priorite-haute',
      'moyenne': 'priorite-moyenne',
      'basse': 'priorite-basse'
    };
    return classes[priorite] || '';
  }

  getTypeIcon(type: string): string {
    const icons: { [key: string]: string } = {
      'urgente': '⚠️',
      'maintenance': '🔧',
      'info': 'ℹ️',
      'systeme': '⚙️'
    };
    return icons[type] || '📌';
  }
}
