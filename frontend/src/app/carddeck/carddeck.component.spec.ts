import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarddeckComponent } from './carddeck.component';

describe('CarddeckComponent', () => {
  let component: CarddeckComponent;
  let fixture: ComponentFixture<CarddeckComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CarddeckComponent]
    });
    fixture = TestBed.createComponent(CarddeckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
