import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameviewComponent } from './gameview.component';

describe('GameviewComponent', () => {
  let component: GameviewComponent;
  let fixture: ComponentFixture<GameviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GameviewComponent]
    });
    fixture = TestBed.createComponent(GameviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
