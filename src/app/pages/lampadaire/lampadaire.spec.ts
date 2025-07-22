import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Lampadaire } from './lampadaire';

describe('Lampadaire', () => {
  let component: Lampadaire;
  let fixture: ComponentFixture<Lampadaire>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Lampadaire]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Lampadaire);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
