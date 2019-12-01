import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Scenario, Step} from "./model/app-scenario.model";
import {Observable} from "rxjs";

export interface StepsResponse {
  steps: number;
}

export interface KeywordsResponse {
  keywords: number;
}

export interface NoActorResponse {
  stepsWithoutActors: string [];
}

export interface DocumentationResponse {
  documentationSteps: string [];
}

export interface FlattenScenarioResponse {
  steps: Step [];
}

export const BASE_API_URL = 'http://localhost:8080/api/scenario';

@Injectable({providedIn: "root"})
export class ScenarioService {

  constructor(private httpClient: HttpClient) {
  }

  getSteps(scenario: Scenario): Observable<StepsResponse> {
    return this.httpClient.post<StepsResponse>(BASE_API_URL + '/steps', scenario);
  }

  getKeywordsAmount(scenario: Scenario): Observable<KeywordsResponse> {
    return this.httpClient.post<KeywordsResponse>(BASE_API_URL + '/keywords', scenario);
  }

  getStepsWithoutActor(scenario: Scenario): Observable<NoActorResponse> {
    return this.httpClient.post<NoActorResponse>(BASE_API_URL + '/no-actor', scenario);
  }

  getPrettyPrintedScenario(scenario: Scenario): Observable<DocumentationResponse> {
    return this.httpClient.post<DocumentationResponse>(BASE_API_URL + '/documentation', scenario);
  }

  getScenariosAtProvidedLevel(scenario: Scenario, level: number): Observable<FlattenScenarioResponse> {
    return this.httpClient.post<FlattenScenarioResponse>(BASE_API_URL + String(level), scenario);
  }

}
