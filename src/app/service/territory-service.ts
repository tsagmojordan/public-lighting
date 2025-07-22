import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TerritoryService {

  constructor() { }
  private regions: { id: number; nom: string }[] = [];
  private departments: { id: number; nom: string; region_id: number }[] = [];
  private arrondissements: { id: number; nom: string; departement_id: number }[] = [];
  private villes: { id: number; nom: string; arrondissement_id: number }[] = [];

  getRegion() {
    return this.regions = [
      { id: 0, nom: 'National' },
      { id: 1, nom: 'Littoral' },
      { id: 2, nom: 'Ouest' },
      { id: 3, nom: 'Nord-Ouest' },
      { id: 4, nom: 'Sud-Ouest' },
      { id: 5, nom: 'Adamaoua' },
      { id: 6, nom: 'Nord' },
      { id: 7, nom: 'Sud' },
      { id: 8, nom: 'Est' },
      { id: 9, nom: 'Centre' },
      { id: 10, nom: 'Extrême-Nord' }
    ];
  }

  getDepartmentByRegion(region: string) {
    switch (region.toLowerCase()) {
      case 'littoral':
        return this.departments = [
          { id: 1, nom: 'Le Nkam', region_id: 1 },
          { id: 2, nom: 'La Sanaga-Maritime', region_id: 1 },
          { id: 3, nom: 'Le Moungo', region_id: 1 },
          { id: 4, nom: 'Le Wouri', region_id: 1 }
        ];
      case 'ouest':
        return this.departments = [
          { id: 41, nom: "Bamboutos", region_id: 8 },
          { id: 42, nom: "Haut-Nkam", region_id: 8 },
          { id: 43, nom: "Menoua", region_id: 8 },
          { id: 44, nom: "Mifi", region_id: 8 },
          { id: 45, nom: "Noun", region_id: 8 },
          { id: 46, nom: "Hauts-Plateaux", region_id: 8 },
          { id: 47, nom: "Koung-Khi", region_id: 8 },
          { id: 48, nom: "Nde", region_id: 8 },
        ];
      case 'nord-ouest':
        return this.departments = [
          { id: 34, nom: "Bui", region_id: 7 },
          { id: 35, nom: "Menchum", region_id: 7 },
          { id: 36, nom: "Ngo-Ketunjia", region_id: 7 },
          { id: 37, nom: "Mezam", region_id: 7 },
          { id: 38, nom: "Boyo", region_id: 7 },
          { id: 39, nom: "Momo", region_id: 7 },
          { id: 40, nom: "Donga-Mantung", region_id: 7 },
        ];
      case 'sud-ouest':
        return this.departments = [
          { id: 18, nom: 'Fako', region_id: 4 },
          { id: 19, nom: 'Lebialem', region_id: 4 },
          { id: 20, nom: 'Manyu', region_id: 4 },
          { id: 21, nom: 'Ndian', region_id: 4 },
          { id: 22, nom: 'Kupe-Manengouba', region_id: 4 },
          { id: 23, nom: 'Meme', region_id: 4 }
        ];
      case 'adamaoua':
        return this.departments = [
          { id: 24, nom: 'Djérem', region_id: 5 },
          { id: 25, nom: 'Vina', region_id: 5 },
          { id: 26, nom: 'Faro et Deo', region_id: 5 },
          { id: 27, nom: 'Mayo-Banyo', region_id: 5 },
          { id: 28, nom: 'Maro', region_id: 5 }
        ];
      case 'nord':
        return this.departments = [
          { id: 29, nom: 'Bénoué', region_id: 6 },
          { id: 30, nom: 'Faro', region_id: 6 },
          { id: 31, nom: 'Mayo-Louti', region_id: 6 },
          { id: 32, nom: 'Mayo-Kani', region_id: 6 },
          { id: 33, nom: 'Mayo-Rey', region_id: 6 }
        ];
      case 'sud':
        return this.departments = [
          { id: 34, nom: 'Océan', region_id: 7 },
          { id: 35, nom: 'Vallée du Ntem', region_id: 7 },
          { id: 36, nom: 'Le Dja et Lobo', region_id: 7 },
          { id: 37, nom: 'Mvila', region_id: 7 }
        ];
      case 'est':
        return this.departments = [
          { id: 38, nom: 'Boumba-et-Ngoko', region_id: 8 },
          { id: 39, nom: 'Haut-Nyong', region_id: 8 },
          { id: 40, nom: 'Lom-et-Djerem', region_id: 8 },
          { id: 41, nom: 'Kadey', region_id: 8 }
        ];
      case 'centre':
        return this.departments = [
          { id: 6, nom: "Haute-Sanaga", region_id: 2 },
          { id: 7, nom: "Lekie", region_id: 2 },
          { id: 8, nom: "Mbam-et-Inoubou", region_id: 2 },
          { id: 9, nom: "Nyong-et-Mfoumou", region_id: 2 },
          { id: 10, nom: "Nyong-et-Soo", region_id: 2 },
          { id: 11, nom: "Mbam-et-Kim", region_id: 2 },
          { id: 12, nom: "Mefou-et-Afamba", region_id: 2 },
          { id: 13, nom: "Mfoundi", region_id: 2 },
          { id: 14, nom: "Nyong-et-Kelle", region_id: 2 },
          { id: 15, nom: "Mefou-et-Akono", region_id: 2 }
        ];
      case 'extrême-nord':
        return this.departments = [
          { id: 20, nom: "Diamare", region_id: 4 },
          { id: 21, nom: "Logone-et-Chari", region_id: 4 },
          { id: 22, nom: "Mayo-Sava", region_id: 4 },
          { id: 23, nom: "Mayo-Tsanaga", region_id: 4 },
          { id: 24, nom: "Mayo-Danay", region_id: 4 },
          { id: 25, nom: "Mayo-Kani", region_id: 4 }
        ];
      default:
        return [];
    }
  }

  getArrondissementByDepartement(department: string) {
    switch (department.toLowerCase()) {
      // Littoral
      case 'le nkam':
        return this.arrondissements = [
          { id: 1, nom: 'Yabassi', departement_id: 1 }
        ];
      case 'la sanaga-maritime':
        return this.arrondissements = [
          { id: 2, nom: 'Edea I', departement_id: 2 },
          { id: 3, nom: 'Edea II', departement_id: 2 }
        ];
      case 'le moungo':
        return this.arrondissements = [
          { id: 4, nom: 'Nkongsamba I', departement_id: 3 },
          { id: 5, nom: 'Nkongsamba II', departement_id: 3 },
          { id: 6, nom: 'Nkongsamba III', departement_id: 3 },
          { id: 7, nom: 'Melong', departement_id: 3 },
          { id: 8, nom: 'Loum', departement_id: 3 },
          { id: 9, nom: 'Dibombari', departement_id: 3 }
        ];
      case 'le wouri':
        return this.arrondissements = [
          { id: 10, nom: 'Douala I', departement_id: 4 },
          { id: 11, nom: 'Douala II', departement_id: 4 },
          { id: 12, nom: 'Douala III', departement_id: 4 },
          { id: 13, nom: 'Douala IV', departement_id: 4 },
          { id: 14, nom: 'Douala V', departement_id: 4 },
          { id: 15, nom: 'Douala VI', departement_id: 4 }
        ];
      // Ouest
      case 'la menoua':
        return this.arrondissements = [
          { id: 16, nom: 'Dschang', departement_id: 5 },
          { id: 17, nom: 'Santchou', departement_id: 5 }
        ];
      case 'le haut-nkam':
        return this.arrondissements = [
          { id: 18, nom: 'Bafang', departement_id: 6 }
        ];
      case 'le koung-khi':
        return this.arrondissements = [
          { id: 19, nom: 'Bandjoun', departement_id: 7 },
          { id: 20, nom: 'Bayangam', departement_id: 7 }
        ];
      case 'le ndé':
        return this.arrondissements = [
          { id: 21, nom: 'Bazou', departement_id: 8 },
          { id: 22, nom: 'Bangangté', departement_id: 8 }
        ];
      case 'les bamboutos':
        return this.arrondissements = [
          { id: 23, nom: 'Mbouda', departement_id: 9 }
        ];
      case 'le mifi':
        return this.arrondissements = [
          { id: 24, nom: 'Bafoussam I', departement_id: 10 },
          { id: 25, nom: 'Bafoussam II', departement_id: 10 },
          { id: 26, nom: 'Bafoussam III', departement_id: 10 }
        ];
      case 'le noun':
        return this.arrondissements = [
          { id: 27, nom: 'Foumbot', departement_id: 11 },
          { id: 28, nom: 'Koutaba', departement_id: 11 }
        ];
      // Nord-Ouest
      case 'mezam':
        return this.arrondissements = [
          { id: 29, nom: 'Bamenda I', departement_id: 12 },
          { id: 30, nom: 'Bamenda II', departement_id: 12 },
          { id: 31, nom: 'Bamenda III', departement_id: 12 },
          { id: 32, nom: 'Bafut', departement_id: 12 }
        ];
      case 'boyo':
        return this.arrondissements = [
          { id: 33, nom: 'Fundong', departement_id: 13 }
        ];
      case 'ngoketunjia':
        return this.arrondissements = [
          { id: 34, nom: 'Ndop', departement_id: 14 },
          { id: 35, nom: 'Balikumbat', departement_id: 14 }
        ];
      case 'donga-mantung':
        return this.arrondissements = [
          { id: 36, nom: 'Nkambe', departement_id: 15 }
        ];
      case 'bui':
        return this.arrondissements = [
          { id: 37, nom: 'Kumbo', departement_id: 16 },
          { id: 38, nom: 'Nkor', departement_id: 16 }
        ];
      case 'momo':
        return this.arrondissements = [
          { id: 39, nom: 'Batibo', departement_id: 17 },
          { id: 40, nom: 'Mbengwi', departement_id: 17 }
        ];
      // Sud-Ouest
      case 'fako':
        return this.arrondissements = [
          { id: 41, nom: 'Buea', departement_id: 18 },
          { id: 42, nom: 'Limbe I', departement_id: 18 },
          { id: 43, nom: 'Limbe II', departement_id: 18 },
          { id: 44, nom: 'Limbe III', departement_id: 18 },
          { id: 45, nom: 'Tiko', departement_id: 18 },
          { id: 46, nom: 'Muyuka', departement_id: 18 }
        ];
      case 'lebialem':
        return this.arrondissements = [
          { id: 47, nom: 'Fontem', departement_id: 19 }
        ];
      case 'manyu':
        return this.arrondissements = [
          { id: 48, nom: 'Mamfe', departement_id: 20 },
          { id: 49, nom: 'Akwaya', departement_id: 20 }
        ];
      case 'ndian':
        return this.arrondissements = [
          { id: 50, nom: 'Mundemba', departement_id: 21 }
        ];
      case 'kupe-manengouba':
        return this.arrondissements = [
          { id: 51, nom: 'Bangem', departement_id: 22 }
        ];
      case 'meme':
        return this.arrondissements = [
          { id: 52, nom: 'Kumba I', departement_id: 23 },
          { id: 53, nom: 'Kumba II', departement_id: 23 },
          { id: 54, nom: 'Kumba III', departement_id: 23 }
        ];
      // Adamaoua
      case 'djérem':
        return this.arrondissements = [
          { id: 55, nom: 'Meiganga', departement_id: 24 },
          { id: 56, nom: 'Tibati', departement_id: 24 }
        ];
      case 'vina':
        return this.arrondissements = [
          { id: 57, nom: 'Ngaoundéré I', departement_id: 25 },
          { id: 58, nom: 'Ngaoundéré II', departement_id: 25 },
          { id: 59, nom: 'Ngaoundéré III', departement_id: 25 }
        ];
      case 'faro et deo':
        return this.arrondissements = [
          { id: 60, nom: 'Galim-Tignère', departement_id: 26 },
          { id: 61, nom: 'Kontcha', departement_id: 26 }
        ];
      case 'mayo-banyo':
        return this.arrondissements = [
          { id: 62, nom: 'Banyo', departement_id: 27 }
        ];
      case 'maro':
        return this.arrondissements = [];
      // Nord
      case 'bénoué':
        return this.arrondissements = [
          { id: 63, nom: 'Garoua I', departement_id: 29 },
          { id: 64, nom: 'Garoua II', departement_id: 29 },
          { id: 65, nom: 'Garoua III', departement_id: 29 }
        ];
      case 'faro':
        return this.arrondissements = [
          { id: 66, nom: 'Poli', departement_id: 30 }
        ];
      case 'mayo-louti':
        return this.arrondissements = [
          { id: 67, nom: 'Guider', departement_id: 31 },
          { id: 68, nom: 'Figuil', departement_id: 31 }
        ];
      case 'mayo-kani':
        return this.arrondissements = [
          { id: 69, nom: 'Kaélé', departement_id: 32 }
        ];
      case 'mayo-rey':
        return this.arrondissements = [
          { id: 70, nom: 'Tcholliré', departement_id: 33 }
        ];
      // Sud
      case 'océan':
        return this.arrondissements = [
          { id: 71, nom: 'Kribi I', departement_id: 34 },
          { id: 72, nom: 'Kribi II', departement_id: 34 }
        ];
      case 'vallée du ntem':
        return this.arrondissements = [
          { id: 73, nom: 'Ambam', departement_id: 35 },
          { id: 74, nom: 'Ma\'an', departement_id: 35 }
        ];
      case 'le dja et lobo':
        return this.arrondissements = [
          { id: 75, nom: 'Sangmelima', departement_id: 36 },
          { id: 76, nom: 'Meyomessala', departement_id: 36 }
        ];
      case 'mvila':
        return this.arrondissements = [
          { id: 77, nom: 'Ebolowa I', departement_id: 37 },
          { id: 78, nom: 'Ebolowa II', departement_id: 37 }
        ];
      // Est
      case 'boumba-et-ngoko':
        return this.arrondissements = [
          { id: 79, nom: 'Yokadouma', departement_id: 38 },
          { id: 80, nom: 'Moloundou', departement_id: 38 }
        ];
      case 'haut-nyong':
        return this.arrondissements = [
          { id: 81, nom: 'Bertoua I', departement_id: 39 },
          { id: 82, nom: 'Bertoua II', departement_id: 39 }
        ];
      case 'lom-et-djerem':
        return this.arrondissements = [
          { id: 83, nom: 'Bétaré-Oya', departement_id: 40 },
          { id: 84, nom: 'Garoua-Boulaï', departement_id: 40 }
        ];
      case 'kadey':
        return this.arrondissements = [
          { id: 85, nom: 'Batouri', departement_id: 41 }
        ];
      // Centre
      case 'mfoundi':
        return this.arrondissements = [
          { id: 86, nom: 'Yaoundé I', departement_id: 42 },
          { id: 87, nom: 'Yaoundé II', departement_id: 42 },
          { id: 88, nom: 'Yaoundé III', departement_id: 42 },
          { id: 89, nom: 'Yaoundé IV', departement_id: 42 },
          { id: 90, nom: 'Yaoundé V', departement_id: 42 },
          { id: 91, nom: 'Yaoundé VI', departement_id: 42 },
          { id: 92, nom: 'Yaoundé VII', departement_id: 42 }
        ];
      case 'mbam-et-inoubou':
        return this.arrondissements = [];
      case 'nyong-et-so\'o':
        return this.arrondissements = [
          { id: 93, nom: 'Mbalmayo', departement_id: 44 }
        ];
      case 'haute-sanaga':
        return this.arrondissements = [
          { id: 94, nom: 'Nanga-Eboko', departement_id: 45 }
        ];
      case 'mefou et afamba':
        return this.arrondissements = [
          { id: 95, nom: 'Ngoumou', departement_id: 46 },
          { id: 96, nom: 'Awae', departement_id: 46 }
        ];
      case 'lékie':
        return this.arrondissements = [
          { id: 97, nom: 'Monatélé', departement_id: 47 },
          { id: 98, nom: 'Obala', departement_id: 47 },
          { id: 99, nom: 'Okola', departement_id: 47 },
          { id: 100, nom: 'Sa\'a', departement_id: 47 }
        ];
      case 'mbam-et-kim':
        return this.arrondissements = [];
      case 'nyong-et-mfoumou':
        return this.arrondissements = [
          { id: 101, nom: 'Eséka', departement_id: 49 },
          { id: 102, nom: 'Ayos', departement_id: 49 }
        ];
      // Extrême-Nord
      case 'mayo-sava':
        return this.arrondissements = [
          { id: 103, nom: 'Mora', departement_id: 50 },
          { id: 104, nom: 'Tokombéré', departement_id: 50 }
        ];
      case 'mayo-tsanaga':
        return this.arrondissements = [
          { id: 105, nom: 'Mokolo', departement_id: 51 },
          { id: 106, nom: 'Koza', departement_id: 51 }
        ];
      case 'logone-et-chari':
        return this.arrondissements = [
          { id: 107, nom: 'Kousseri', departement_id: 52 }
        ];
      case 'diamaré':
        return this.arrondissements = [
          { id: 108, nom: 'Maroua I', departement_id: 53 },
          { id: 109, nom: 'Maroua II', departement_id: 53 },
          { id: 110, nom: 'Maroua III', departement_id: 53 }
        ];
      default:
        return [];
    }
  }

  getTownByArrondissement(arrondissement: string) {
    switch (arrondissement.toLowerCase()) {
      // Littoral
      case 'yabassi':
        return this.villes = [
          { id: 1, nom: 'Yabassi Centre', arrondissement_id: 1 }
        ];
      case 'edea i':
        return this.villes = [
          { id: 2, nom: 'Edea Centre', arrondissement_id: 2 }
        ];
      case 'edea ii':
        return this.villes = [
          { id: 3, nom: 'Dizangué', arrondissement_id: 3 }
        ];
      case 'nkongsamba i':
        return this.villes = [
          { id: 4, nom: 'Nkongsamba Centre', arrondissement_id: 4 }
        ];
      case 'nkongsamba ii':
        return this.villes = [
          { id: 5, nom: 'Ekangté', arrondissement_id: 5 }
        ];
      case 'melong':
        return this.villes = [
          { id: 6, nom: 'Melong Ville', arrondissement_id: 7 }
        ];
      case 'loum':
        return this.villes = [
          { id: 7, nom: 'Loum Ville', arrondissement_id: 8 }
        ];
      case 'dibombari':
        return this.villes = [
          { id: 8, nom: 'Dibombari Centre', arrondissement_id: 9 }
        ];
      case 'douala i':
        return this.villes = [
          { id: 9, nom: 'Akwa', arrondissement_id: 10 },
          { id: 10, nom: 'Bonabéri', arrondissement_id: 10 }
        ];
      case 'douala ii':
        return this.villes = [
          { id: 11, nom: 'Bali', arrondissement_id: 11 },
          { id: 12, nom: 'Deido', arrondissement_id: 11 }
        ];
      case 'douala iii':
        return this.villes = [
          { id: 13, nom: 'Japoma', arrondissement_id: 12 },
          { id: 14, nom: 'Bassa', arrondissement_id: 12 }
        ];
      case 'douala iv':
        return this.villes = [
          { id: 15, nom: 'Nylon', arrondissement_id: 13 }
        ];
      case 'douala v':
        return this.villes = [
          { id: 16, nom: 'Bonamoussadi', arrondissement_id: 14 },
          { id: 17, nom: 'Logpom', arrondissement_id: 14 },
          { id: 18, nom: 'Ndokoti', arrondissement_id: 14 },
          { id: 19, nom: 'Cité des Palmiers', arrondissement_id: 14 }
        ];
      // Ouest
      case 'dschang':
        return this.villes = [
          { id: 20, nom: 'Dschang Ville', arrondissement_id: 16 },
          { id: 21, nom: 'Fongo-Tongo', arrondissement_id: 16 }
        ];
      case 'santchou':
        return this.villes = [
          { id: 22, nom: 'Santchou Ville', arrondissement_id: 17 }
        ];
      case 'bafang':
        return this.villes = [
          { id: 23, nom: 'Bafang Ville', arrondissement_id: 18 }
        ];
      case 'bandjoun':
        return this.villes = [
          { id: 24, nom: 'Bandjoun Ville', arrondissement_id: 19 }
        ];
      case 'bayangam':
        return this.villes = [
          { id: 25, nom: 'Bayangam Ville', arrondissement_id: 20 }
        ];
      case 'bazou':
        return this.villes = [
          { id: 26, nom: 'Bazou Ville', arrondissement_id: 21 }
        ];
      case 'bangangté':
        return this.villes = [
          { id: 27, nom: 'Bangangté Ville', arrondissement_id: 22 }
        ];
      case 'mbouda':
        return this.villes = [
          { id: 28, nom: 'Mbouda Ville', arrondissement_id: 23 }
        ];
      case 'bafoussam i':
        return this.villes = [
          { id: 29, nom: 'Bafoussam Marché', arrondissement_id: 24 }
        ];
      case 'bafoussam ii':
        return this.villes = [
          { id: 30, nom: 'Djeleng', arrondissement_id: 25 }
        ];
      case 'bafoussam iii':
        return this.villes = [
          { id: 31, nom: 'Kamkop', arrondissement_id: 26 }
        ];
      case 'foumbot':
        return this.villes = [
          { id: 32, nom: 'Foumbot Ville', arrondissement_id: 27 }
        ];
      case 'koutaba':
        return this.villes = [
          { id: 33, nom: 'Koutaba Ville', arrondissement_id: 28 }
        ];
      // Nord-Ouest
      case 'bamenda i':
        return this.villes = [
          { id: 34, nom: 'Nkwen', arrondissement_id: 29 },
          { id: 35, nom: 'Upstation', arrondissement_id: 29 }
        ];
      case 'bamenda ii':
        return this.villes = [
          { id: 36, nom: 'Mankon', arrondissement_id: 30 }
        ];
      case 'bafut':
        return this.villes = [
          { id: 37, nom: 'Bafut Centre', arrondissement_id: 32 }
        ];
      case 'fundong':
        return this.villes = [
          { id: 38, nom: 'Fundong Town', arrondissement_id: 33 }
        ];
      case 'ndop':
        return this.villes = [
          { id: 39, nom: 'Ndop Centre', arrondissement_id: 34 }
        ];
      case 'balikumbat':
        return this.villes = [
          { id: 40, nom: 'Balikumbat Village', arrondissement_id: 35 }
        ];
      case 'nkambe':
        return this.villes = [
          { id: 41, nom: 'Nkambe Town', arrondissement_id: 36 }
        ];
      case 'kumbo':
        return this.villes = [
          { id: 42, nom: 'Kumbo Town', arrondissement_id: 37 }
        ];
      case 'nkor':
        return this.villes = [
          { id: 43, nom: 'Nkor Town', arrondissement_id: 38 }
        ];
      case 'batibo':
        return this.villes = [
          { id: 44, nom: 'Batibo Village', arrondissement_id: 39 }
        ];
      case 'mbengwi':
        return this.villes = [
          { id: 45, nom: 'Mbengwi Town', arrondissement_id: 40 }
        ];
      // Sud-Ouest
      case 'buea':
        return this.villes = [
          { id: 46, nom: 'Molyko', arrondissement_id: 41 },
          { id: 47, nom: 'Great Soppo', arrondissement_id: 41 }
        ];
      case 'limbe i':
        return this.villes = [
          { id: 48, nom: 'Limbe Town', arrondissement_id: 42 },
          { id: 49, nom: 'Mile 4', arrondissement_id: 42 }
        ];
      case 'tiko':
        return this.villes = [
          { id: 50, nom: 'Tiko Town', arrondissement_id: 45 }
        ];
      case 'muyuka':
        return this.villes = [
          { id: 51, nom: 'Muyuka Town', arrondissement_id: 46 }
        ];
      case 'fontem':
        return this.villes = [
          { id: 52, nom: 'Fontem Town', arrondissement_id: 47 }
        ];
      case 'mamfe':
        return this.villes = [
          { id: 53, nom: 'Mamfe Town', arrondissement_id: 48 }
        ];
      case 'akwaya':
        return this.villes = [
          { id: 54, nom: 'Akwaya Village', arrondissement_id: 49 }
        ];
      case 'mundemba':
        return this.villes = [
          { id: 55, nom: 'Mundemba Town', arrondissement_id: 50 }
        ];
      case 'bangem':
        return this.villes = [
          { id: 56, nom: 'Bangem Town', arrondissement_id: 51 }
        ];
      case 'kumba i':
        return this.villes = [
          { id: 57, nom: 'Kumba Town Centre', arrondissement_id: 52 }
        ];
      case 'kumba ii':
        return this.villes = [
          { id: 58, nom: 'Fiango', arrondissement_id: 53 }
        ];
      case 'kumba iii':
        return this.villes = [
          { id: 59, nom: 'Kosala', arrondissement_id: 54 }
        ];
      // Adamaoua
      case 'meiganga':
        return this.villes = [
          { id: 60, nom: 'Meiganga Ville', arrondissement_id: 55 }
        ];
      case 'tibati':
        return this.villes = [
          { id: 61, nom: 'Tibati Ville', arrondissement_id: 56 }
        ];
      case 'ngaoundéré i':
        return this.villes = [
          { id: 62, nom: 'Ngaoundéré Centre Ville', arrondissement_id: 57 }
        ];
      case 'ngaoundéré ii':
        return this.villes = [
          { id: 63, nom: 'Joli Soir', arrondissement_id: 58 }
        ];
      case 'galim-tignère':
        return this.villes = [
          { id: 64, nom: 'Galim-Tignère Ville', arrondissement_id: 60 }
        ];
      case 'kontcha':
        return this.villes = [
          { id: 65, nom: 'Kontcha Ville', arrondissement_id: 61 }
        ];
      case 'banyo':
        return this.villes = [
          { id: 66, nom: 'Banyo Ville', arrondissement_id: 62 }
        ];
      // Nord
      case 'garoua i':
        return this.villes = [
          { id: 67, nom: 'Garoua Centre Ville', arrondissement_id: 63 }
        ];
      case 'garoua ii':
        return this.villes = [
          { id: 68, nom: 'Poumpoumré', arrondissement_id: 64 }
        ];
      case 'poli':
        return this.villes = [
          { id: 69, nom: 'Poli Ville', arrondissement_id: 66 }
        ];
      case 'guider':
        return this.villes = [
          { id: 70, nom: 'Guider Ville', arrondissement_id: 67 }
        ];
      case 'figuil':
        return this.villes = [
          { id: 71, nom: 'Figuil Ville', arrondissement_id: 68 }
        ];
      case 'kaélé':
        return this.villes = [
          { id: 72, nom: 'Kaélé Ville', arrondissement_id: 69 }
        ];
      case 'tcholliré':
        return this.villes = [
          { id: 73, nom: 'Tcholliré Ville', arrondissement_id: 70 }
        ];
      // Sud
      case 'kribi i':
        return this.villes = [
          { id: 74, nom: 'Kribi Centre Ville', arrondissement_id: 71 }
        ];
      case 'kribi ii':
        return this.villes = [
          { id: 75, nom: 'Grand Batanga', arrondissement_id: 72 }
        ];
      case 'ambam':
        return this.villes = [
          { id: 76, nom: 'Ambam Ville', arrondissement_id: 73 }
        ];
      case 'ma\'an':
        return this.villes = [
          { id: 77, nom: 'Ma\'an Ville', arrondissement_id: 74 }
        ];
      case 'sangmelima':
        return this.villes = [
          { id: 78, nom: 'Sangmelima Ville', arrondissement_id: 75 }
        ];
      case 'meyomessala':
        return this.villes = [
          { id: 79, nom: 'Meyomessala Ville', arrondissement_id: 76 }
        ];
      case 'ebolowa i':
        return this.villes = [
          { id: 80, nom: 'Ebolowa Ville', arrondissement_id: 77 }
        ];
      case 'ebolowa ii':
        return this.villes = [
          { id: 81, nom: 'Nkolandom', arrondissement_id: 78 }
        ];
      // Est
      case 'yokadouma':
        return this.villes = [
          { id: 82, nom: 'Yokadouma Ville', arrondissement_id: 79 }
        ];
      case 'moloundou':
        return this.villes = [
          { id: 83, nom: 'Moloundou Ville', arrondissement_id: 80 }
        ];
      case 'bertoua i':
        return this.villes = [
          { id: 84, nom: 'Bertoua Centre Ville', arrondissement_id: 81 }
        ];
      case 'bertoua ii':
        return this.villes = [
          { id: 85, nom: 'Kano', arrondissement_id: 82 }
        ];
      case 'bétaré-oya':
        return this.villes = [
          { id: 86, nom: 'Bétaré-Oya Ville', arrondissement_id: 83 }
        ];
      case 'garoua-boulaï':
        return this.villes = [
          { id: 87, nom: 'Garoua-Boulaï Ville', arrondissement_id: 84 }
        ];
      case 'batouri':
        return this.villes = [
          { id: 88, nom: 'Batouri Ville', arrondissement_id: 85 }
        ];
      // Centre
      case 'yaoundé i':
        return this.villes = [
          { id: 89, nom: 'Nlongkak', arrondissement_id: 86 },
          { id: 90, nom: 'Tsinga', arrondissement_id: 86 }
        ];
      case 'yaoundé ii':
        return this.villes = [
          { id: 91, nom: 'Mokolo', arrondissement_id: 87 },
          { id: 92, nom: 'Elig-Essono', arrondissement_id: 87 }
        ];
      case 'yaoundé iii':
        return this.villes = [
          { id: 93, nom: 'Mvog-Ada', arrondissement_id: 88 }
        ];
      case 'yaoundé iv':
        return this.villes = [
          { id: 94, nom: 'Ekoumdoum', arrondissement_id: 89 }
        ];
      case 'yaoundé v':
        return this.villes = [
          { id: 95, nom: 'Ngousso', arrondissement_id: 90 },
          { id: 96, nom: 'Mfandena', arrondissement_id: 90 }
        ];
      case 'yaoundé vi':
        return this.villes = [
          { id: 97, nom: 'Bastos', arrondissement_id: 91 },
          { id: 98, nom: 'Biyem-Assi', arrondissement_id: 91 }
        ];
      case 'yaoundé vii':
        return this.villes = [
          { id: 99, nom: 'Nkoabang', arrondissement_id: 92 }
        ];
      case 'mbalmayo':
        return this.villes = [
          { id: 100, nom: 'Mbalmayo Centre', arrondissement_id: 93 }
        ];
      case 'nanga-eboko':
        return this.villes = [
          { id: 101, nom: 'Nanga-Eboko Ville', arrondissement_id: 94 }
        ];
      case 'ngoumou':
        return this.villes = [
          { id: 102, nom: 'Ngoumou Ville', arrondissement_id: 95 }
        ];
      case 'awae':
        return this.villes = [
          { id: 103, nom: 'Awae Ville', arrondissement_id: 96 }
        ];
      case 'monatélé':
        return this.villes = [
          { id: 104, nom: 'Monatélé Ville', arrondissement_id: 97 }
        ];
      case 'obala':
        return this.villes = [
          { id: 105, nom: 'Obala Ville', arrondissement_id: 98 }
        ];
      case 'okola':
        return this.villes = [
          { id: 106, nom: 'Okola Ville', arrondissement_id: 99 }
        ];
      case 'sa\'a':
        return this.villes = [
          { id: 107, nom: 'Sa\'a Ville', arrondissement_id: 100 }
        ];
      case 'eséka':
        return this.villes = [
          { id: 108, nom: 'Eséka Ville', arrondissement_id: 101 }
        ];
      case 'ayos':
        return this.villes = [
          { id: 109, nom: 'Ayos Ville', arrondissement_id: 102 }
        ];
      // Extrême-Nord
      case 'mora':
        return this.villes = [
          { id: 110, nom: 'Mora Ville', arrondissement_id: 103 }
        ];
      case 'tokombéré':
        return this.villes = [
          { id: 111, nom: 'Tokombéré Ville', arrondissement_id: 104 }
        ];
      case 'mokolo':
        return this.villes = [
          { id: 112, nom: 'Mokolo Ville', arrondissement_id: 105 }
        ];
      case 'koza':
        return this.villes = [
          { id: 113, nom: 'Koza Ville', arrondissement_id: 106 }
        ];
      case 'kousseri':
        return this.villes = [
          { id: 114, nom: 'Kousseri Ville', arrondissement_id: 107 },
          { id: 115, nom: 'Magada', arrondissement_id: 107 }
        ];
      case 'maroua i':
        return this.villes = [
          { id: 116, nom: 'Maroua Centre Ville', arrondissement_id: 108 }
        ];
      case 'maroua ii':
        return this.villes = [
          { id: 117, nom: 'Kongola', arrondissement_id: 109 }
        ];
      default:
        return [];
    }
  }
}


