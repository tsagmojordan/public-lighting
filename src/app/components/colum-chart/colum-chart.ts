import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Chart} from 'chart.js';
import {FormsModule} from '@angular/forms';


class RegionData {
  region: string = '';
  pannes: number[] = [];
  depannages: number[] = [];

}

@Component({
  selector: 'app-colum-chart',
  imports: [
    FormsModule
  ],
  templateUrl: './colum-chart.html',
  styleUrl: './colum-chart.css'
})
export class ColumChart implements OnInit{

  @ViewChild('chartCanvas', { static: true }) chartCanvas!: ElementRef<HTMLCanvasElement>;

  private chart: Chart | null = null;
  selectedRegion: string = 'all';
  showPercentage: boolean = false;

  // Périodes temporelles
  private timeLabels = ['Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Jun', 'Jul', 'Aoû', 'Sep', 'Oct', 'Nov', 'Déc'];

  // Données par région
  regions: RegionData[] = [
    {
      region: 'Centre',
      pannes: [45, 52, 38, 41, 47, 55, 62, 58, 49, 43, 39, 35],
      depannages: [42, 48, 35, 38, 44, 52, 58, 55, 46, 40, 36, 33]
    },
    {
      region: 'Nord',
      pannes: [32, 38, 29, 35, 41, 48, 52, 49, 42, 36, 31, 28],
      depannages: [30, 35, 27, 33, 38, 45, 49, 46, 39, 34, 29, 26]
    },
    {
      region: 'Sud',
      pannes: [28, 34, 25, 31, 36, 42, 46, 43, 37, 32, 27, 24],
      depannages: [26, 31, 23, 29, 33, 39, 43, 40, 35, 30, 25, 22]
    },
    {
      region: 'Est',
      pannes: [38, 44, 32, 37, 43, 50, 55, 52, 45, 39, 34, 30],
      depannages: [35, 41, 30, 34, 40, 47, 52, 49, 42, 36, 31, 28]
    },
    {
      region: 'Ouest',
      pannes: [41, 47, 35, 40, 46, 53, 58, 55, 48, 42, 37, 33],
      depannages: [38, 44, 32, 37, 43, 50, 55, 52, 45, 39, 34, 30]
    }
  ];

  ngOnInit(): void {
    this.createChart();
  }

  private createChart(): void {
    const ctx = this.chartCanvas.nativeElement.getContext('2d');

    if (ctx) {
      this.chart = new Chart(ctx, {
        type: 'bar',
        data: this.getChartData(),
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            title: {
              display: true,
              text: this.getChartTitle(),
              font: {
                size: 16,
                weight: 'bold'
              }
            },
            legend: {
              display: true,
              position: 'top',
              labels: {
                usePointStyle: true,
                padding: 20
              }
            },
            tooltip: {
              mode: 'index',
              intersect: false,
              backgroundColor: 'rgba(0, 0, 0, 0.8)',
              titleColor: '#fff',
              bodyColor: '#fff',
              borderColor: '#fff',
              borderWidth: 1,
              callbacks: {
                label: (context) => {
                  const suffix = this.showPercentage ? '%' : ' lampadaires';
                  return `${context.dataset.label}: ${context.parsed.y}${suffix}`;
                }
              }
            }
          },
          scales: {
            x: {
              display: true,
              title: {
                display: true,
                text: 'Période',
                font: {
                  weight: 'bold'
                }
              },
              grid: {
                display: false
              }
            },
            y: {
              display: true,
              title: {
                display: true,
                text: this.showPercentage ? 'Pourcentage (%)' : 'Nombre de lampadaires',
                font: {
                  weight: 'bold'
                }
              },
              beginAtZero: true,
              max: this.showPercentage ? 100 : undefined,
              grid: {
                display: true,
                color: 'rgba(0, 0, 0, 0.1)'
              }
            }
          },
          interaction: {
            mode: 'nearest',
            axis: 'x',
            intersect: false
          }
        }
      });
    }
  }

  private getChartData() {
    if (this.selectedRegion === 'all') {
      // Agréger toutes les régions
      const totalPannes = this.timeLabels.map((_, index) =>
        this.regions.reduce((sum, region) => sum + region.pannes[index], 0)
      );
      const totalDepannages = this.timeLabels.map((_, index) =>
        this.regions.reduce((sum, region) => sum + region.depannages[index], 0)
      );

      return {
        labels: this.timeLabels,
        datasets: [
          {
            label: 'Lampadaires en Panne',
            data: this.showPercentage ? this.convertToPercentage(totalPannes, totalDepannages, 'pannes') : totalPannes,
            backgroundColor: 'rgba(255, 99, 132, 0.8)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 2,
            borderRadius: 4,
            borderSkipped: false
          },
          {
            label: 'Lampadaires Dépannés',
            data: this.showPercentage ? this.convertToPercentage(totalPannes, totalDepannages, 'depannages') : totalDepannages,
            backgroundColor: 'rgba(75, 192, 192, 0.8)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 2,
            borderRadius: 4,
            borderSkipped: false
          }
        ]
      };
    } else {
      // Données pour une région spécifique
      const regionData = this.regions.find(r => r.region === this.selectedRegion);
      if (regionData) {
        return {
          labels: this.timeLabels,
          datasets: [
            {
              label: 'Lampadaires en Panne',
              data: this.showPercentage ? this.convertToPercentage(regionData.pannes, regionData.depannages, 'pannes') : regionData.pannes,
              backgroundColor: 'rgba(255, 99, 132, 0.8)',
              borderColor: 'rgba(255, 99, 132, 1)',
              borderWidth: 2,
              borderRadius: 4,
              borderSkipped: false
            },
            {
              label: 'Lampadaires Dépannés',
              data: this.showPercentage ? this.convertToPercentage(regionData.pannes, regionData.depannages, 'depannages') : regionData.depannages,
              backgroundColor: 'rgba(75, 192, 192, 0.8)',
              borderColor: 'rgba(75, 192, 192, 1)',
              borderWidth: 2,
              borderRadius: 4,
              borderSkipped: false
            }
          ]
        };
      }
    }
    return { labels: [], datasets: [] };
  }

  private convertToPercentage(pannes: number[], depannages: number[], type: 'pannes' | 'depannages'): number[] {
    return pannes.map((panne, index) => {
      const total = panne + depannages[index];
      if (total === 0) return 0;
      return Math.round((type === 'pannes' ? panne : depannages[index]) / total * 100);
    });
  }

  private getChartTitle(): string {
    const regionText = this.selectedRegion === 'all' ? 'Dépannages Mensuelle' : `Région ${this.selectedRegion}`;
    const viewText = this.showPercentage ? 'Répartition (%)' : 'Évolutions Pannes';
    return `${viewText} - ${regionText}`;
  }

  onRegionChange(): void {
    if (this.chart) {
      this.chart.data = this.getChartData();
      this.chart.options.plugins!.title!.text = this.getChartTitle();
      this.chart.update();
    }
  }

  toggleView(): void {
    this.showPercentage = !this.showPercentage;
    if (this.chart) {
      this.chart.data = this.getChartData();
      this.chart.options.plugins!.title!.text = this.getChartTitle();
      // @ts-ignore
      this.chart.options.scales!.y.title!.text = this.showPercentage ? 'Pourcentage (%)' : 'Nombre de lampadaires';
      // @ts-ignore
      this.chart.options.scales!.y.max = this.showPercentage ? 100 : undefined;
      this.chart.update();
    }
  }

  getTotalPannes(): number {
    if (this.selectedRegion === 'all') {
      return this.regions.reduce((total, region) =>
        total + region.pannes.reduce((sum, val) => sum + val, 0), 0
      );
    } else {
      const regionData = this.regions.find(r => r.region === this.selectedRegion);
      return regionData ? regionData.pannes.reduce((sum, val) => sum + val, 0) : 0;
    }
  }

  getTotalDepannages(): number {
    if (this.selectedRegion === 'all') {
      return this.regions.reduce((total, region) =>
        total + region.depannages.reduce((sum, val) => sum + val, 0), 0
      );
    } else {
      const regionData = this.regions.find(r => r.region === this.selectedRegion);
      return regionData ? regionData.depannages.reduce((sum, val) => sum + val, 0) : 0;
    }
  }

  getResolutionRate(): number {
    const totalPannes = this.getTotalPannes();
    const totalDepannages = this.getTotalDepannages();
    if (totalPannes === 0) return 0;
    return Math.round((totalDepannages / totalPannes) * 100);
  }

  ngOnDestroy(): void {
    if (this.chart) {
      this.chart.destroy();
    }
  }
}
