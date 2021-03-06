// Copyright 2020 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.sdlc.server.guice;

import com.google.inject.Binder;
import org.finos.legend.sdlc.domain.model.project.Project;
import org.finos.legend.sdlc.server.BaseLegendSDLCServer;
import org.finos.legend.sdlc.server.domain.api.backup.BackupApi;
import org.finos.legend.sdlc.server.domain.api.build.BuildApi;
import org.finos.legend.sdlc.server.domain.api.comparison.ComparisonApi;
import org.finos.legend.sdlc.server.domain.api.conflictResolution.ConflictResolutionApi;
import org.finos.legend.sdlc.server.domain.api.entity.EntityApi;
import org.finos.legend.sdlc.server.domain.api.issue.IssueApi;
import org.finos.legend.sdlc.server.domain.api.project.ProjectApi;
import org.finos.legend.sdlc.server.domain.api.project.ProjectConfigurationApi;
import org.finos.legend.sdlc.server.domain.api.review.ReviewApi;
import org.finos.legend.sdlc.server.domain.api.revision.RevisionApi;
import org.finos.legend.sdlc.server.domain.api.user.UserApi;
import org.finos.legend.sdlc.server.domain.api.version.VersionApi;
import org.finos.legend.sdlc.server.domain.api.workspace.WorkspaceApi;
import org.finos.legend.sdlc.server.gitlab.GitLabConfiguration;
import org.finos.legend.sdlc.server.gitlab.auth.GitLabUserContext;
import org.finos.legend.sdlc.server.gitlab.resources.GitLabAuthResource;
import org.finos.legend.sdlc.server.inmemory.backend.InMemoryBackend;
import org.finos.legend.sdlc.server.inmemory.backend.api.InMemoryBackupApi;
import org.finos.legend.sdlc.server.inmemory.backend.api.InMemoryBuildApi;
import org.finos.legend.sdlc.server.inmemory.backend.api.InMemoryComparisonApi;
import org.finos.legend.sdlc.server.inmemory.backend.api.InMemoryConflictResolutionApi;
import org.finos.legend.sdlc.server.inmemory.backend.api.InMemoryEntityApi;
import org.finos.legend.sdlc.server.inmemory.backend.api.InMemoryIssueApi;
import org.finos.legend.sdlc.server.inmemory.backend.api.InMemoryProjectApi;
import org.finos.legend.sdlc.server.inmemory.backend.api.InMemoryProjectConfigurationApi;
import org.finos.legend.sdlc.server.inmemory.backend.api.InMemoryReviewApi;
import org.finos.legend.sdlc.server.inmemory.backend.api.InMemoryRevisionApi;
import org.finos.legend.sdlc.server.inmemory.backend.api.InMemoryUserApi;
import org.finos.legend.sdlc.server.inmemory.backend.api.InMemoryVersionApi;
import org.finos.legend.sdlc.server.inmemory.backend.api.InMemoryWorkspaceApi;
import org.finos.legend.sdlc.server.inmemory.domain.api.InMemoryProject;

public class InMemoryModule extends AbstractBaseModule
{
    public InMemoryModule(BaseLegendSDLCServer<?> server)
    {
        super(server);
    }

    @Override
    protected void configureApis(Binder binder)
    {
        binder.bind(InMemoryBackend.class);
        binder.bind(ProjectApi.class).to(InMemoryProjectApi.class);
        binder.bind(ProjectConfigurationApi.class).to(InMemoryProjectConfigurationApi.class);
        binder.bind(UserApi.class).to(InMemoryUserApi.class);
        binder.bind(IssueApi.class).to(InMemoryIssueApi.class);
        binder.bind(EntityApi.class).to(InMemoryEntityApi.class);
        binder.bind(WorkspaceApi.class).to(InMemoryWorkspaceApi.class);
        binder.bind(RevisionApi.class).to(InMemoryRevisionApi.class);
        binder.bind(ReviewApi.class).to(InMemoryReviewApi.class);
        binder.bind(BuildApi.class).to(InMemoryBuildApi.class);
        binder.bind(VersionApi.class).to(InMemoryVersionApi.class);
        binder.bind(ComparisonApi.class).to(InMemoryComparisonApi.class);
        binder.bind(ConflictResolutionApi.class).to(InMemoryConflictResolutionApi.class);
        binder.bind(BackupApi.class).to(InMemoryBackupApi.class);
        binder.bind(GitLabUserContext.class);
        binder.bind(GitLabAuthResource.class);
        binder.bind(GitLabConfiguration.class).toProvider(() -> getConfiguration().getGitLabConfiguration());

        binder.bind(Project.class).to(InMemoryProject.class);
    }
}
