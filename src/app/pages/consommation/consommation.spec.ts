import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Consommation } from './consommation';

describe('Consommation', () => {
  let component: Consommation;
  let fixture: ComponentFixture<Consommation>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Consommation]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Consommation);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
