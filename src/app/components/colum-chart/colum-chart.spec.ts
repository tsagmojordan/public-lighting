import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColumChart } from './colum-chart';

describe('ColumChart', () => {
  let component: ColumChart;
  let fixture: ComponentFixture<ColumChart>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ColumChart]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ColumChart);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
