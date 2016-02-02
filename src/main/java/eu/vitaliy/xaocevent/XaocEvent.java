package eu.vitaliy.xaocevent;

import org.springframework.context.ApplicationEvent;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * @author Vitaliy Oliynyk
 */

public class XaocEvent extends ApplicationEvent {

    private Object eventData;
    private String eventID;
    private Metadata metadata = new Metadata(false);
    public XaocEvent(Object source, Object eventData, String eventID) {
        super(source);
        this.eventData = eventData;
        this.eventID   = eventID;
    }

    public Object getEventData() {
        return eventData;
    }

    public void setEventData(Object eventData) {
        this.eventData = eventData;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    protected static class Metadata
    {
        private boolean fromAnnotatedObservable;

        public Metadata() {
        }

        public Metadata(boolean fromAnnotatedObservable) {
            this.fromAnnotatedObservable = fromAnnotatedObservable;
        }

        public boolean isFromAnnotatedObservable() {
            return fromAnnotatedObservable;
        }

        public void setFromAnnotatedObservable(boolean fromAnnotatedObservable) {
            this.fromAnnotatedObservable = fromAnnotatedObservable;
        }
    }
}
