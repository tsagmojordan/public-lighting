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
    },
    {
      id: 'LP-YDE-001',
      localisation: 'Carrefour Warda, Yaoundé',
      region: 'Centre',
      etat: 'Opérationnel',
      consommation: '55W',
      derniereMaintenance: '15/05/2025'
    },
    {
      id: 'LP-DLA-002',
      localisation: 'Boulevard de la Liberté, Douala',
      region: 'Littoral',
      etat: 'En panne',
      consommation: '48W',
      derniereMaintenance: '03/07/2025'
    },
    {
      id: 'LP-BDA-003',
      localisation: 'Place Ahmadou Ahidjo, Bamenda',
      region: 'Nord-Ouest',
      etat: 'Maintenance',
      consommation: '42W',
      derniereMaintenance: '12/06/2025'
    },
    {
      id: 'LP-GRA-004',
      localisation: 'Rue Mokolo, Garoua',
      region: 'Nord',
      etat: 'Opérationnel',
      consommation: '60W',
      derniereMaintenance: '22/08/2025'
    },
    {
      id: 'LP-MAR-005',
      localisation: 'Marché Central, Maroua',
      region: 'Extrême-Nord',
      etat: 'En panne',
      consommation: '50W',
      derniereMaintenance: '09/09/2025'
    },
    {
      id: 'LP-BER-006',
      localisation: 'Avenue du 20 Mai, Bertoua',
      region: 'Est',
      etat: 'Opérationnel',
      consommation: '58W',
      derniereMaintenance: '28/04/2025'
    },
    {
      id: 'LP-EBO-007',
      localisation: 'Carrefour Nkongsamba, Ebolowa',
      region: 'Sud',
      etat: 'En panne',
      consommation: '47W',
      derniereMaintenance: '18/07/2025'
    },
    {
      id: 'LP-BAF-008',
      localisation: 'Rond-point BIAO, Bafoussam',
      region: 'Ouest',
      etat: 'Opérationnel',
      consommation: '53W',
      derniereMaintenance: '10/10/2025'
    },
    {
      id: 'LP-NGA-009',
      localisation: 'Place du 11 Février, Ngaoundéré',
      region: 'Adamaoua',
      etat: 'Maintenance',
      consommation: '61W',
      derniereMaintenance: '14/03/2025'
    },
    {
      id: 'LP-KOU-010',
      localisation: 'Marché Mboppi, Douala',
      region: 'Littoral',
      etat: 'En panne',
      consommation: '45W',
      derniereMaintenance: '20/05/2025'
    },
    {
      id: 'LP-YDE-011',
      localisation: 'Entrée Simbock, Yaoundé',
      region: 'Centre',
      etat: 'Opérationnel',
      consommation: '56W',
      derniereMaintenance: '01/06/2025'
    },
    {
      id: 'LP-DLA-012',
      localisation: 'Bonapriso, Douala',
      region: 'Littoral',
      etat: 'Opérationnel',
      consommation: '52W',
      derniereMaintenance: '29/07/2025'
    },
    {
      id: 'LP-BUE-013',
      localisation: 'Molyko Stadium, Buea',
      region: 'Sud-Ouest',
      etat: 'En panne',
      consommation: '49W',
      derniereMaintenance: '03/08/2025'
    },
    {
      id: 'LP-YDE-014',
      localisation: 'Carrefour Elig-Edzoa, Yaoundé',
      region: 'Centre',
      etat: 'Maintenance',
      consommation: '62W',
      derniereMaintenance: '06/04/2025'
    },
    {
      id: 'LP-DSC-015',
      localisation: 'Place des Fêtes, Dschang',
      region: 'Ouest',
      etat: 'Opérationnel',
      consommation: '54W',
      derniereMaintenance: '19/05/2025'
    },
    {
      id: 'LP-BER-016',
      localisation: 'Boulevard du 1er Mai, Bertoua',
      region: 'Est',
      etat: 'Opérationnel',
      consommation: '59W',
      derniereMaintenance: '08/02/2025'
    },
    {
      id: 'LP-EBO-017',
      localisation: 'Carrefour Nko’ovos, Ebolowa',
      region: 'Sud',
      etat: 'En panne',
      consommation: '43W',
      derniereMaintenance: '12/06/2025'
    },
    {
      id: 'LP-MAR-018',
      localisation: 'Rond-point Dougoï, Maroua',
      region: 'Extrême-Nord',
      etat: 'Opérationnel',
      consommation: '51W',
      derniereMaintenance: '09/07/2025'
    },
    {
      id: 'LP-NGA-019',
      localisation: 'Université de Ngaoundéré',
      region: 'Adamaoua',
      etat: 'Maintenance',
      consommation: '63W',
      derniereMaintenance: '14/09/2025'
    },
    {
      id: 'LP-BDA-020',
      localisation: 'Commercial Avenue, Bamenda',
      region: 'Nord-Ouest',
      etat: 'En panne',
      consommation: '46W',
      derniereMaintenance: '05/03/2025'
    },
    {
      id: 'LP-YDE-021',
      localisation: 'Avenue Kennedy, Yaoundé',
      region: 'Centre',
      etat: 'Opérationnel',
      consommation: '57W',
      derniereMaintenance: '24/08/2025'
    },
    {
      id: 'LP-DLA-022',
      localisation: 'Bonanjo, Douala',
      region: 'Littoral',
      etat: 'Maintenance',
      consommation: '64W',
      derniereMaintenance: '11/01/2025'
    },
    {
      id: 'LP-BUE-023',
      localisation: 'Clarks Quarters, Buea',
      region: 'Sud-Ouest',
      etat: 'Opérationnel',
      consommation: '50W',
      derniereMaintenance: '17/05/2025'
    },
    {
      id: 'LP-GRA-024',
      localisation: 'Marché de Garoua, Garoua',
      region: 'Nord',
      etat: 'En panne',
      consommation: '44W',
      derniereMaintenance: '22/10/2025'
    },
    {
      id: 'LP-BAF-025',
      localisation: 'Entrée Ville, Bafoussam',
      region: 'Ouest',
      etat: 'Opérationnel',
      consommation: '58W',
      derniereMaintenance: '27/06/2025'
    },
    {
      id: 'LP-EBO-026',
      localisation: 'Boulevard Paul Biya, Ebolowa',
      region: 'Sud',
      etat: 'Maintenance',
      consommation: '65W',
      derniereMaintenance: '02/03/2025'
    },
    {
      id: 'LP-NGA-027',
      localisation: 'Rond-point Dang, Ngaoundéré',
      region: 'Adamaoua',
      etat: 'Opérationnel',
      consommation: '55W',
      derniereMaintenance: '10/07/2025'
    },
    {
      id: 'LP-MAR-028',
      localisation: 'Carrefour Pitoaré, Maroua',
      region: 'Extrême-Nord',
      etat: 'En panne',
      consommation: '48W',
      derniereMaintenance: '16/05/2025'
    },
    {
      id: 'LP-YDE-029',
      localisation: 'Ekounou, Yaoundé',
      region: 'Centre',
      etat: 'Opérationnel',
      consommation: '59W',
      derniereMaintenance: '12/06/2025'
    },
    {
      id: 'LP-DLA-030',
      localisation: 'Ndokoti, Douala',
      region: 'Littoral',
      etat: 'En panne',
      consommation: '47W',
      derniereMaintenance: '30/09/2025'
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
