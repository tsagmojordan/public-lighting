import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DasbordMinee } from './dasbord-minee';

describe('DasbordMinee', () => {
  let component: DasbordMinee;
  let fixture: ComponentFixture<DasbordMinee>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DasbordMinee]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DasbordMinee);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
