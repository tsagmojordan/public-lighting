import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictionAi } from './prediction-ai';

describe('PredictionAi', () => {
  let component: PredictionAi;
  let fixture: ComponentFixture<PredictionAi>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PredictionAi]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PredictionAi);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
