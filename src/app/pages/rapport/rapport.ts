import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {Sidebar} from '../../components/sidebar/sidebar';
import {Navbar} from '../../components/navbar/navbar';

interface Rapport {
  id: string;
  titre: string;
  type: 'performance' | 'maintenance' | 'consommation' | 'pannes' | 'financier';
  description: string;
  periode: string;
  dateGeneration: string;
  auteur: string;
  region: string;
  statut: 'publie' | 'brouillon' | 'archive';
  taille: string;
  nbPages: number;
}

interface StatRapport {
  total: number;
  publies: number;
  brouillons: number;
  cesMois: number;
}

@Component({
  selector: 'app-rapports',
  standalone: true,
  imports: [CommonModule, FormsModule, Sidebar, Navbar],
  templateUrl: './rapport.html',
  styleUrls: ['./rapport.css']
})
export class Rapports implements OnInit {
  rapports: Rapport[] = [];
  filteredRapports: Rapport[] = [];

  // Filtres
  selectedType: string = 'all';
  selectedRegion: string = 'all';
  selectedStatut: string = 'all';
  selectedPeriode: string = 'all';
  searchTerm: string = '';

  // Stats
  stats: StatRapport = {
    total: 0,
    publies: 0,
    brouillons: 0,
    cesMois: 0
  };

  // Régions du Cameroun
  regions: string[] = [
    'Centre',
    'Littoral',
    'Ouest',
    'Nord',
    'Extrême-Nord',
    'Adamaoua',
    'Nord-Ouest',
    'Sud-Ouest',
    'Est',
    'Sud',
    'National'
  ];

  // Modal export
  showExportModal: boolean = false;
  selectedRapportForExport: Rapport | null = null;
  exportFormat: string = 'pdf';

  ngOnInit() {
    this.loadRapports();
    this.calculateStats();
    this.applyFilters();
  }

  loadRapports() {
    this.rapports = [
      {
        id: 'RAP-001',
        titre: 'Rapport Mensuel de Performance',
        type: 'performance',
        description: 'Analyse détaillée des performances de l\'éclairage public pour le mois d\'octobre 2025',
        periode: 'Octobre 2025',
        dateGeneration: '27/10/2025',
        auteur: 'Ministère de l\'Énergie',
        region: 'National',
        statut: 'publie',
        taille: '2.4 MB',
        nbPages: 45
      },
      {
        id: 'RAP-002',
        titre: 'État des Maintenances - Yaoundé',
        type: 'maintenance',
        description: 'Rapport complet sur les interventions de maintenance effectuées dans la région Centre',
        periode: 'T3 2025',
        dateGeneration: '25/10/2025',
        auteur: 'Service Technique Centre',
        region: 'Centre',
        statut: 'publie',
        taille: '1.8 MB',
        nbPages: 32
      },
      {
        id: 'RAP-003',
        titre: 'Analyse de Consommation Énergétique',
        type: 'consommation',
        description: 'Étude comparative de la consommation énergétique par région et par tranche horaire',
        periode: 'Septembre 2025',
        dateGeneration: '23/10/2025',
        auteur: 'Direction Technique',
        region: 'National',
        statut: 'brouillon',
        taille: '3.1 MB',
        nbPages: 58
      },
      {
        id: 'RAP-004',
        titre: 'Rapport des Pannes - Douala',
        type: 'pannes',
        description: 'Inventaire et analyse des pannes détectées dans la région du Littoral',
        periode: 'Octobre 2025',
        dateGeneration: '20/10/2025',
        auteur: 'Service Technique Littoral',
        region: 'Littoral',
        statut: 'publie',
        taille: '1.2 MB',
        nbPages: 28
      },
      {
        id: 'RAP-005',
        titre: 'Bilan Financier Trimestriel',
        type: 'financier',
        description: 'Rapport financier des dépenses d\'exploitation et de maintenance du réseau',
        periode: 'T3 2025',
        dateGeneration: '18/10/2025',
        auteur: 'Direction Financière',
        region: 'National',
        statut: 'publie',
        taille: '950 KB',
        nbPages: 22
      },
      {
        id: 'RAP-006',
        titre: 'Performance Éclairage - Bafoussam',
        type: 'performance',
        description: 'Évaluation de la performance des lampadaires dans la région Ouest',
        periode: 'Octobre 2025',
        dateGeneration: '15/10/2025',
        auteur: 'Service Technique Ouest',
        region: 'Ouest',
        statut: 'publie',
        taille: '1.5 MB',
        nbPages: 35
      },
      {
        id: 'RAP-007',
        titre: 'Maintenance Préventive - Nord',
        type: 'maintenance',
        description: 'Planification et suivi des maintenances préventives région Nord',
        periode: 'T4 2025',
        dateGeneration: '12/10/2025',
        auteur: 'Service Technique Nord',
        region: 'Nord',
        statut: 'brouillon',
        taille: '2.1 MB',
        nbPages: 41
      },
      {
        id: 'RAP-008',
        titre: 'Consommation Nocturne - National',
        type: 'consommation',
        description: 'Analyse de la consommation énergétique pendant les heures de pointe nocturnes',
        periode: 'Août 2025',
        dateGeneration: '10/10/2025',
        auteur: 'Direction Technique',
        region: 'National',
        statut: 'archive',
        taille: '2.8 MB',
        nbPages: 52
      },
      {
        id: 'RAP-009',
        titre: 'Incidents et Pannes - Extrême-Nord',
        type: 'pannes',
        description: 'Rapport détaillé des incidents techniques dans l\'Extrême-Nord',
        periode: 'Septembre 2025',
        dateGeneration: '08/10/2025',
        auteur: 'Service Technique Extrême-Nord',
        region: 'Extrême-Nord',
        statut: 'publie',
        taille: '1.1 MB',
        nbPages: 26
      },
      {
        id: 'RAP-010',
        titre: 'Rapport Annuel 2024',
        type: 'performance',
        description: 'Synthèse annuelle complète de l\'ensemble des activités et performances',
        periode: 'Année 2024',
        dateGeneration: '05/10/2025',
        auteur: 'Ministère de l\'Énergie',
        region: 'National',
        statut: 'publie',
        taille: '5.2 MB',
        nbPages: 120
      }
    ];
  }

  calculateStats() {
    this.stats.total = this.rapports.length;
    this.stats.publies = this.rapports.filter(r => r.statut === 'publie').length;
    this.stats.brouillons = this.rapports.filter(r => r.statut === 'brouillon').length;

    // Rapports d'octobre 2025
    this.stats.cesMois = this.rapports.filter(r =>
      r.periode.includes('Octobre 2025') || r.dateGeneration.includes('10/2025')
    ).length;
  }

  applyFilters() {
    this.filteredRapports = this.rapports.filter(rapport => {
      const matchType = this.selectedType === 'all' || rapport.type === this.selectedType;
      const matchRegion = this.selectedRegion === 'all' || rapport.region === this.selectedRegion;
      const matchStatut = this.selectedStatut === 'all' || rapport.statut === this.selectedStatut;
      const matchPeriode = this.selectedPeriode === 'all' || rapport.periode.includes(this.selectedPeriode);
      const matchSearch = this.searchTerm === '' ||
        rapport.titre.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        rapport.description.toLowerCase().includes(this.searchTerm.toLowerCase());

      return matchType && matchRegion && matchStatut && matchPeriode && matchSearch;
    });
  }

  onFilterChange() {
    this.applyFilters();
  }

  openExportModal(rapport: Rapport) {
    this.selectedRapportForExport = rapport;
    this.showExportModal = true;
    this.exportFormat = 'pdf';
  }

  closeExportModal() {
    this.showExportModal = false;
    this.selectedRapportForExport = null;
  }

  exportRapport() {
    if (!this.selectedRapportForExport) return;

    const rapport = this.selectedRapportForExport;

    // Simulation de l'export
    console.log(`Export du rapport "${rapport.titre}" au format ${this.exportFormat.toUpperCase()}`);

    // Message de confirmation
    const formatName = this.getFormatName(this.exportFormat);
    alert(`✅ Rapport exporté avec succès au format ${formatName}!\n\nFichier: ${rapport.id}_${rapport.titre.replace(/\s+/g, '_')}.${this.exportFormat}`);

    this.closeExportModal();
  }

  getFormatName(format: string): string {
    const formats: { [key: string]: string } = {
      'pdf': 'PDF',
      'excel': 'Excel (XLSX)',
      'word': 'Word (DOCX)',
      'csv': 'CSV',
      'json': 'JSON'
    };
    return formats[format] || format.toUpperCase();
  }

  getTypeClass(type: string): string {
    const classes: { [key: string]: string } = {
      'performance': 'type-badge-performance',
      'maintenance': 'type-badge-maintenance',
      'consommation': 'type-badge-consommation',
      'pannes': 'type-badge-pannes',
      'financier': 'type-badge-financier'
    };
    return classes[type] || '';
  }

  getStatutClass(statut: string): string {
    const classes: { [key: string]: string } = {
      'publie': 'statut-badge-publie',
      'brouillon': 'statut-badge-brouillon',
      'archive': 'statut-badge-archive'
    };
    return classes[statut] || '';
  }

  getTypeIcon(type: string): string {
    const icons: { [key: string]: string } = {
      'performance': '📊',
      'maintenance': '🔧',
      'consommation': '⚡',
      'pannes': '⚠️',
      'financier': '💰'
    };
    return icons[type] || '📄';
  }

  visualiserRapport(rapport: Rapport) {
    alert(`Ouverture du rapport: ${rapport.titre}\n\nCette fonctionnalité ouvre une vue détaillée du rapport.`);
  }

  supprimerRapport(id: string) {
    const rapport = this.rapports.find(r => r.id === id);
    if (confirm(`Êtes-vous sûr de vouloir supprimer le rapport "${rapport?.titre}" ?`)) {
      this.rapports = this.rapports.filter(r => r.id !== id);
      this.calculateStats();
      this.applyFilters();
    }
  }

  genererNouveauRapport() {
    alert('🔄 Génération d\'un nouveau rapport...\n\nCette fonctionnalité ouvre un assistant de création de rapport.');
  }

  exporterTout() {
    if (confirm(`Exporter tous les rapports filtrés (${this.filteredRapports.length}) au format ZIP ?`)) {
      alert(`✅ Export de ${this.filteredRapports.length} rapports en cours...\n\nFichier: rapports_export_${new Date().toISOString().split('T')[0]}.zip`);
    }
  }
}
