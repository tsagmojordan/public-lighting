// lampadaire-management.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {Sidebar} from '../../components/sidebar/sidebar';
import {Navbar} from '../../components/navbar/navbar';

interface Lampadaire {
  id: string;
  localisation: string;
  region: string;
  etat: 'Opérationnel' | 'En panne' | 'Maintenance';
  consommation: string;
  derniereMaintenance: string;
}

@Component({
  selector: 'lampadaire',
  standalone: true,
  imports: [CommonModule, FormsModule, Sidebar, Navbar],
  templateUrl: 'lampadaire.html',
  styleUrl:'lampadaire.css'
})

export class LampadaireManagementComponent implements OnInit {
  lampadaires: Lampadaire[] = [
    {
      id: 'LP-YDE-001',
      localisation: 'Avenue Kennedy, Yaoundé',
      region: 'Centre',
      etat: 'Opérationnel',
      consommation: '45W',
      derniereMaintenance: '15/05/2025'
    },
    {
      id: 'LP-DLA-002',
      localisation: 'Boulevard du 20 Mai, Douala',
      region: 'Littoral',
      etat: 'En panne',
      consommation: '0W',
      derniereMaintenance: '10/05/2025'
    },
    {
      id: 'LP-BDA-003',
      localisation: 'Place Ahmadou Ahidjo, Bamenda',
      region: 'Nord-Ouest',
      etat: 'Maintenance',
      consommation: '42W',
      derniereMaintenance: '12/06/2025'
    }
  ];

  filteredLampadaires: Lampadaire[] = [];
  searchTerm: string = '';
  filterEtat: string = '';
  filterRegion: string = '';

  ngOnInit() {
    this.filteredLampadaires = [...this.lampadaires];
  }

  filterLampadaires() {
    this.filteredLampadaires = this.lampadaires.filter(lamp => {
      const matchSearch = !this.searchTerm ||
        lamp.id.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        lamp.localisation.toLowerCase().includes(this.searchTerm.toLowerCase());

      const matchEtat = !this.filterEtat || lamp.etat === this.filterEtat;
      const matchRegion = !this.filterRegion || lamp.region === this.filterRegion;

      return matchSearch && matchEtat && matchRegion;
    });
  }

  ajouterLampadaire() {
    console.log('Ajouter un nouveau lampadaire');
    alert('Fonction d\'ajout à implémenter');
  }

  modifier(lampadaire: Lampadaire) {
    console.log('Modifier:', lampadaire);
    alert(`Modifier le lampadaire ${lampadaire.id}`);
  }

  controler(lampadaire: Lampadaire) {
    console.log('Contrôler:', lampadaire);
    alert(`Contrôler le lampadaire ${lampadaire.id}`);
  }

  details(lampadaire: Lampadaire) {
    console.log('Détails:', lampadaire);
    alert(`Détails du lampadaire ${lampadaire.id}`);
  }

  exporterCSV() {
    const headers = ['ID Lampadaire', 'Localisation', 'Région', 'État', 'Consommation', 'Dernière Maintenance'];
    const rows = this.filteredLampadaires.map(lamp => [
      lamp.id,
      lamp.localisation,
      lamp.region,
      lamp.etat,
      lamp.consommation,
      lamp.derniereMaintenance
    ]);

    let csv = headers.join(',') + '\n';
    rows.forEach(row => {
      csv += row.map(cell => `"${cell}"`).join(',') + '\n';
    });

    const blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    const url = URL.createObjectURL(blob);
    link.setAttribute('href', url);
    link.setAttribute('download', 'lampadaires.csv');
    link.style.visibility = 'hidden';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }
}
