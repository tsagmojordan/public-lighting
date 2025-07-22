import {Component, OnInit, ViewChild, ElementRef, Input} from '@angular/core';
import { Chart, registerables } from 'chart.js';

// Enregistrer tous les composants Chart.js
Chart.register(...registerables);

@Component({
  selector: 'app-linear-chart',
  template: `
    <div class="chart-container">
<!--      <h2>Consommation et Économie d'Énergie</h2>-->
      <canvas #chartCanvas width="400" height="200"></canvas>
      <div class="controls">
<!--        <button (click)="updateData()" class="btn-update">-->
<!--          Mettre à jour les données-->
<!--        </button>-->
      </div>
    </div>
  `,
  styles: [`
    .chart-container {
      box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
      margin-top: 25px;
      border-radius: 10px;
      width: 38.3vw;
      height: 50vh;
      padding: 20px;
    }

    h2 {
      text-align: center;
      color: #333;
      margin-bottom: 20px;
    }

    canvas {
      max-height: 400px;
    }

    .controls {
      text-align: center;
      margin-top: 20px;
    }

    .btn-update {
      background-color: #4CAF50;
      color: white;
      padding: 10px 20px;
     // box-shadow: #cccccc;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px;
    }

    .btn-update:hover {
      background-color: #45a049;
    }
  `]
})
export class LinearChart implements OnInit {
  @ViewChild('chartCanvas', { static: true }) chartCanvas!: ElementRef<HTMLCanvasElement>;

  private chart: Chart | null = null;

  // Données d'exemple
  @Input() private labels = ['Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Jun', 'Jul', 'Aoû', 'Sep', 'Oct', 'Nov', 'Déc'];
  @Input() private energieData = [120, 150, 180, 140, 160, 200, 220, 210, 190, 170, 130, 110];
  @Input() private energieEconomiseeData = [20, 25, 35, 30, 40, 50, 55, 60, 45, 35, 25, 20];

  ngOnInit(): void {
    this.createChart();
  }

  private createChart(): void {
    const ctx = this.chartCanvas.nativeElement.getContext('2d');

    if (ctx) {
      this.chart = new Chart(ctx, {
        type: 'line',
        data: {
          labels: this.labels,
          datasets: [
            {
              label: 'Énergie Consommée (kWh)',
              data: this.energieData,
              borderColor: '#FF6384',
              backgroundColor: 'rgba(255, 99, 132, 0.1)',
              borderWidth: 3,
              fill: true,
              tension: 0.4,
              pointBackgroundColor: '#FF6384',
              pointBorderColor: '#fff',
              pointBorderWidth: 2,
              pointRadius: 6
            },
            {
              label: 'Énergie Économisée (kWh)',
              data: this.energieEconomiseeData,
              borderColor: 'green',
              backgroundColor: 'rgba(54, 162, 235, 0.1)',
              borderWidth: 3,
              fill: true,
              tension: 0.4,
              pointBackgroundColor: 'green',
              pointBorderColor: '#fff',
              pointBorderWidth: 2,
              pointRadius: 6
            }
          ]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            title: {
              display: true,
              text: 'Évolution Énergétique Mensuelle',
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
              borderWidth: 1
            }
          },
          scales: {
            x: {
              display: true,
              title: {
                display: true,
                text: 'Mois',
                font: {
                  weight: 'bold'
                }
              },
              grid: {
                display: true,
                color: 'rgba(0, 0, 0, 0.1)'
              }
            },
            y: {
              display: true,
              title: {
                display: true,
                text: 'Énergie (kWh)',
                font: {
                  weight: 'bold'
                }
              },
              beginAtZero: true,
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
          },
          elements: {
            line: {
              borderJoinStyle: 'round'
            }
          }
        }
      });
    }
  }

  updateData(): void {
    if (this.chart) {
      // Générer de nouvelles données aléatoirement
      this.energieData = this.energieData.map(() => Math.floor(Math.random() * 200) + 100);
      this.energieEconomiseeData = this.energieEconomiseeData.map(() => Math.floor(Math.random() * 60) + 10);

      // Mettre à jour le graphique
      this.chart.data.datasets[0].data = this.energieData;
      this.chart.data.datasets[1].data = this.energieEconomiseeData;
      this.chart.update();
    }
  }

  ngOnDestroy(): void {
    if (this.chart) {
      this.chart.destroy();
    }
  }
}


